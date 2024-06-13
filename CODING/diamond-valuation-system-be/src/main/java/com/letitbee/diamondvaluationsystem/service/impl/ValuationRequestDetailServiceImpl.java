package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.*;
import com.letitbee.diamondvaluationsystem.enums.*;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.DiamondValuationNoteDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestDetailDTO;
import com.letitbee.diamondvaluationsystem.repository.*;
import com.letitbee.diamondvaluationsystem.service.ValuationRequestDetailService;
import com.letitbee.diamondvaluationsystem.utils.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValuationRequestDetailServiceImpl implements ValuationRequestDetailService {

    private ModelMapper mapper;
    private ValuationRequestDetailRepository valuationRequestDetailRepository;
    private ValuationRequestRepository valuationRequestRepository;
    private DiamondValuationNoteRepository diamondValuationNoteRepository;
    private ServicePriceListRepository servicePriceListRepository;
    private DiamondMarketRepository diamondMarketRepository;
    private DiamondValuationAssignRepository diamondValuationAssignRepository;
    private DiamondValuationNoteServiceImpl diamondValuationNoteServiceImpl;

    public ValuationRequestDetailServiceImpl(ModelMapper mapper,
                                             ValuationRequestDetailRepository valuationRequestDetailRepository,
                                             ValuationRequestRepository valuationRequestRepository,
                                             DiamondValuationNoteRepository diamondValuationNoteRepository,
                                             ServicePriceListRepository servicePriceListRepository,
                                             DiamondValuationAssignRepository diamondValuationAssignRepository,
                                             DiamondMarketRepository diamondMarketRepository,
                                             DiamondValuationNoteServiceImpl diamondValuationNoteServiceImpl
    ) {
        this.mapper = mapper;
        this.valuationRequestDetailRepository = valuationRequestDetailRepository;
        this.valuationRequestRepository = valuationRequestRepository;
        this.diamondValuationNoteRepository = diamondValuationNoteRepository;
        this.servicePriceListRepository = servicePriceListRepository;
        this.diamondValuationAssignRepository = diamondValuationAssignRepository;
        this.diamondMarketRepository = diamondMarketRepository;
        this.diamondValuationNoteServiceImpl = diamondValuationNoteServiceImpl;
    }

    private ValuationRequestDetail mapToEntity(ValuationRequestDetailDTO valuationRequestDetailDTO) {
        return mapper.map(valuationRequestDetailDTO, ValuationRequestDetail.class);
    }

    private ValuationRequestDetailDTO mapToDTO(ValuationRequestDetail valuationRequestDetail) {
        ValuationRequestDetailDTO valuationRequestDetailDTO = mapper.map(valuationRequestDetail, ValuationRequestDetailDTO.class);
        if (valuationRequestDetail.getDiamondValuationNote() != null
                && valuationRequestDetail.getDiamondValuationNote().getClarityCharacteristic() != null) {
            DiamondValuationNoteDTO diamondValuationNoteDTO =
                    diamondValuationNoteServiceImpl.getDiamondValuationNoteById(
                            valuationRequestDetail.getDiamondValuationNote().getId());
            valuationRequestDetailDTO.setDiamondValuationNote(diamondValuationNoteDTO);
        }
        return valuationRequestDetailDTO;
    }

    @Override
    public Response<ValuationRequestDetailDTO> getAllValuationRequestDetail(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy) : Sort.by(sortBy).descending();
        //Set size page and pageNo
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ValuationRequestDetail> page = valuationRequestDetailRepository.findAll(pageable);
        List<ValuationRequestDetail> valuationRequestDetails = page.getContent();

        List<ValuationRequestDetailDTO> listDTO = valuationRequestDetails.
                stream().
                map((valuationRequestDetail) -> mapToDTO(valuationRequestDetail)).toList();

        Response<ValuationRequestDetailDTO> response = new Response<>();

        response.setContent(listDTO);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElement(page.getTotalElements());
        response.setLast(page.isLast());

        return response;

    }

    @Override
    public ValuationRequestDetailDTO getValuationRequestDetailById(Long id) {
        ValuationRequestDetail valuationRequestDetail = valuationRequestDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Valuation request detail", "id", id + ""));
        return mapToDTO(valuationRequestDetail);
    }

    @Override
    public ValuationRequestDetailDTO updateValuationRequestDetail(long id, ValuationRequestDetailDTO valuationRequestDetailDTO) {
        //get valuation request detail
        ValuationRequestDetail valuationRequestDetail = valuationRequestDetailRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Valuation request detail", "id", id + ""));
        //set data to valuation request detail
        valuationRequestDetail.setSize(valuationRequestDetailDTO.getSize());
        valuationRequestDetail.setStatus(valuationRequestDetailDTO.getStatus());
        valuationRequestDetail.setResultLink(valuationRequestDetailDTO.getResultLink());
        valuationRequestDetail.setCancelReason(valuationRequestDetailDTO.getCancelReason());
        //delete diamond note when know diamond is fake
        deleteDiamondValuationNote(valuationRequestDetailDTO, valuationRequestDetail);
        valuationRequestDetail.setDiamond(valuationRequestDetailDTO.isDiamond());

        //update Service Price
        if (valuationRequestDetailDTO.isDiamond()) {
            updateServicePrice(valuationRequestDetailDTO.getSize(), valuationRequestDetail);
        }

        //update valuation price base on mode
        updateValuationPriceBaseOnMode(valuationRequestDetailDTO.isMode(), valuationRequestDetail, valuationRequestDetailDTO);
        valuationRequestDetail.setMode(valuationRequestDetailDTO.isMode());

        //save to database
        valuationRequestDetail = valuationRequestDetailRepository.save(valuationRequestDetail);

        ValuationRequest valuationRequest = valuationRequestDetail.getValuationRequest();
        if (valuationRequestDetail.getStatus().toString().equalsIgnoreCase(RequestDetailStatus.CANCEL.toString())
                || valuationRequestDetail.getStatus().toString().equalsIgnoreCase(RequestDetailStatus.ASSESSING.toString())) {
            changeValuationRequestStatusToValuating(valuationRequest);
        } else if (
                valuationRequestDetail.getStatus().toString().equalsIgnoreCase(RequestDetailStatus.ASSESSED.toString())) {
            updateDiamondValuationNote(valuationRequestDetail);//update diamond valuation note price when status id assessed
            generateCertificate(valuationRequestDetail); // generate certificate id and certificate date;
        }
        // update valuation request if valuation request detail status is cancel or assessing
        changeValuationRequestStatusToComplete(valuationRequest); //update valuation request status to complete
        //if all detail is approve or cancel
        valuationRequestDetail = valuationRequestDetailRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Valuation request detail", "id", id + ""));

        return mapToDTO(valuationRequestDetail);
    }

    private void updateValuationRequestStatus(ValuationRequest valuationRequest, RequestStatus requestStatus) {
        valuationRequest.setStatus(requestStatus);
        valuationRequestRepository.save(valuationRequest);
    }

    private void changeValuationRequestStatusToValuating(ValuationRequest valuationRequest) {
        if (valuationRequest.getStatus().toString().equalsIgnoreCase(RequestStatus.RECEIVED.toString())) {
            RequestStatus requestStatus = RequestStatus.VALUATING;
            updateValuationRequestStatus(valuationRequest, requestStatus);
        } // update valuation request if its status is "received"
    }

    private void changeValuationRequestStatusToComplete(ValuationRequest valuationRequest) {
        Set<ValuationRequestDetail> valuationRequestDetailSet = valuationRequest.getValuationRequestDetails();

        boolean checkStatusDetail = true;
        //check status in all valuation request detail
        for (ValuationRequestDetail valuationRequestDetail : valuationRequestDetailSet) {
            if (!(valuationRequestDetail.getStatus().toString().equalsIgnoreCase(RequestDetailStatus.CANCEL.toString())
                    || valuationRequestDetail.getStatus().toString().equalsIgnoreCase(RequestDetailStatus.APPROVED.toString()))) {
                checkStatusDetail = false;
            }
        }
        if (checkStatusDetail) {
            RequestStatus requestStatus = RequestStatus.COMPLETED;
            updateValuationRequestStatus(valuationRequest, requestStatus);
        } // update valuation request if its all detail status is cancel or approve

    }

    private void deleteDiamondValuationNote(ValuationRequestDetailDTO valuationRequestDetailDTO
            , ValuationRequestDetail valuationRequestDetail) {
        if (valuationRequestDetail.isDiamond() && !valuationRequestDetailDTO.isDiamond()) {
            DiamondValuationNote diamondValuationNote = valuationRequestDetail.getDiamondValuationNote();
            diamondValuationNoteRepository.delete(diamondValuationNote);
        }
    }

    private void updateServicePrice(float sizeDTO,
                                    ValuationRequestDetail valuationRequestDetail) {
        com.letitbee.diamondvaluationsystem.entity.Service service = valuationRequestDetail.getValuationRequest().getService();
        ServicePriceList servicePriceList = servicePriceListRepository.findByMinSizeLessThanEqualAndMaxSizeGreaterThanEqualAndService(sizeDTO, sizeDTO, service);
        double servicePrice = servicePriceList.getInitPrice() +
                servicePriceList.getUnitPrice() * (sizeDTO - servicePriceList.getMinSize());
        ValuationRequest valuationRequest = valuationRequestDetail.getValuationRequest();
        double totalPrice = valuationRequest.getTotalServicePrice();
        totalPrice = totalPrice + servicePrice - valuationRequestDetail.getServicePrice();

        valuationRequestDetail.setServicePrice(servicePrice);
        valuationRequest.setTotalServicePrice(totalPrice);
        valuationRequestRepository.save(valuationRequest);
    }

    private void generateCertificate(ValuationRequestDetail valuationRequestDetail) {
        DiamondValuationNote diamondValuationNote = valuationRequestDetail.getDiamondValuationNote();
        Date certificateDate = new Date();
        String certificateId = "";
        do {
            certificateId = Tools.generateId(10);
        } while (diamondValuationNoteRepository.countByCertificateId(certificateId) != 0);
        diamondValuationNote.setCertificateId(certificateId);
        diamondValuationNote.setCertificateDate(certificateDate);
        diamondValuationNoteRepository.save(diamondValuationNote);
    }

    private void updateDiamondValuationNote(ValuationRequestDetail valuationRequestDetail) {
        DiamondValuationNote diamondValuationNoteDTO = valuationRequestDetail.getDiamondValuationNote();
        List<DiamondMarket> diamondMarkets = diamondMarketRepository.findSelectedFieldsByDiamondProperties(
                diamondValuationNoteDTO.getDiamondOrigin(),
                diamondValuationNoteDTO.getCaratWeight(),
                diamondValuationNoteDTO.getColor(),
                diamondValuationNoteDTO.getClarity(),
                diamondValuationNoteDTO.getCut(),
                diamondValuationNoteDTO.getPolish(),
                diamondValuationNoteDTO.getSymmetry(),
                diamondValuationNoteDTO.getShape(),
                diamondValuationNoteDTO.getFluorescence());

        if (diamondMarkets != null && !diamondMarkets.isEmpty()) {
            //get current diamond price
            double fairPrice = 0;
            for (DiamondMarket diamondMarket : diamondMarkets) {
                fairPrice += diamondMarket.getPrice();
            }
            fairPrice = fairPrice / diamondMarkets.size();
            diamondValuationNoteDTO.setFairPrice(fairPrice);
            diamondValuationNoteDTO.setMaxPrice(diamondMarkets.get(diamondMarkets.size() - 1).getPrice());
            diamondValuationNoteDTO.setMinPrice(diamondMarkets.stream().findFirst().get().getPrice());
        }
        diamondValuationNoteRepository.save(mapper.map(diamondValuationNoteDTO, DiamondValuationNote.class));
    }

    private void updateValuationPriceBaseOnMode(boolean mode, ValuationRequestDetail valuationRequestDetail,
                                                ValuationRequestDetailDTO valuationRequestDetailDTO) {
        Set<DiamondValuationAssign> diamondValuationAssigns = valuationRequestDetail.getDiamondValuationAssigns();
        if (diamondValuationAssigns != null) {
            if (mode) {
                int i = 0;
                double valuationPrice = 0;
                for (DiamondValuationAssign diamondValuationAssign : diamondValuationAssigns) {
                    i++;
                    valuationPrice += diamondValuationAssign.getValuationPrice();
                }
                valuationPrice = valuationPrice / i;
                valuationRequestDetail.setValuationPrice(valuationPrice);
            } else {
                if (valuationRequestDetailDTO.getDiamondValuationAssign() != null) {
                    DiamondValuationAssign diamondValuationAssign =
                            diamondValuationAssignRepository.findById(valuationRequestDetailDTO.getDiamondValuationAssign().getId()).
                                    orElseThrow(() -> new ResourceNotFoundException("Diamond Valuation Assign", "id", valuationRequestDetailDTO.getDiamondValuationAssign().getId() + ""));
                    double valuationPrice = diamondValuationAssign.getValuationPrice();
                    valuationRequestDetail.setDiamondValuationAssign(diamondValuationAssign);
                    valuationRequestDetail.setValuationPrice(valuationPrice);
                }

            }
        }
    }

}
