package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.*;
import com.letitbee.diamondvaluationsystem.enums.RequestDetailStatus;
import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.DiamondValuationAssignDTO;
import com.letitbee.diamondvaluationsystem.payload.PostDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.repository.DiamondValuationAssignRepository;
import com.letitbee.diamondvaluationsystem.repository.StaffRepository;
import com.letitbee.diamondvaluationsystem.repository.ValuationRequestDetailRepository;
import com.letitbee.diamondvaluationsystem.service.DiamondValuationAssignService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiamondValuationAssignImpl implements DiamondValuationAssignService {

    private ModelMapper mapper;
    private DiamondValuationAssignRepository diamondValuationAssignRepository;
    private ValuationRequestDetailRepository valuationRequestDetailRepository;
    private StaffRepository staffRepository;


    public DiamondValuationAssignImpl(ModelMapper mapper,
                                      DiamondValuationAssignRepository diamondValuationAssignRepository,
                                      ValuationRequestDetailRepository valuationRequestDetailRepository,
                                      StaffRepository staffRepository) {
        this.mapper = mapper;
        this.diamondValuationAssignRepository = diamondValuationAssignRepository;
        this.valuationRequestDetailRepository = valuationRequestDetailRepository;
        this.staffRepository = staffRepository;
    }
    @Override
    public DiamondValuationAssignDTO createDiamondValuationAssign(DiamondValuationAssignDTO diamondValuationAssignDTO) {
        DiamondValuationAssign diamondValuationAssign = new DiamondValuationAssign();
        Staff staff = staffRepository.findById(diamondValuationAssignDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", diamondValuationAssignDTO.getStaffId() + ""));
        ValuationRequestDetail valuationRequestDetail = valuationRequestDetailRepository
                .findById(diamondValuationAssignDTO.getValuationRequestDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("Valuation request detail", "id", diamondValuationAssignDTO.getValuationRequestDetailId() + ""));

        diamondValuationAssign.setStaff(staff);
        diamondValuationAssign.setValuationRequestDetail(valuationRequestDetail);
        diamondValuationAssign.setValuationPrice(diamondValuationAssignDTO.getValuationPrice());
        diamondValuationAssign.setComment(diamondValuationAssignDTO.getComment());
        diamondValuationAssign.setStatus(diamondValuationAssignDTO.isStatus());
        if (diamondValuationAssign.isStatus()) {
            diamondValuationAssign.setCreationDate((new Date()));
        }
        //save to database
        diamondValuationAssign = diamondValuationAssignRepository.save(diamondValuationAssign);
        return mapToDTO(diamondValuationAssign);
    }

    @Override
    public DiamondValuationAssignDTO updateDiamondValuationAssign(long id, DiamondValuationAssignDTO diamondValuationAssignDTO) {
        DiamondValuationAssign diamondValuationAssign = diamondValuationAssignRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diamond valuation assign", "id", id + ""));
        diamondValuationAssign.setValuationPrice(diamondValuationAssignDTO.getValuationPrice());
        diamondValuationAssign.setComment(diamondValuationAssignDTO.getComment());
        diamondValuationAssign.setStatus(diamondValuationAssignDTO.isStatus());
        diamondValuationAssign.setStaff(staffRepository.findById(diamondValuationAssignDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", diamondValuationAssignDTO.getStaffId() + "")));
        diamondValuationAssign.setValuationRequestDetailId(valuationRequestDetailRepository.findById(diamondValuationAssignDTO.getValuationRequestDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("Valuation request detail", "id", diamondValuationAssignDTO.getValuationRequestDetailId() + "")));
        if (diamondValuationAssign.isStatus()) {
            diamondValuationAssign.setCreationDate((new Date()));
        } // update date when status is true
        diamondValuationAssign.setCommentDetail(diamondValuationAssignDTO.getCommentDetail());
        ValuationRequestDetail valuationRequestDetail = valuationRequestDetailRepository
                .findById(diamondValuationAssignDTO.getValuationRequestDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("Valuation request detail", "id", diamondValuationAssignDTO.getValuationRequestDetailId() + ""));
        int flag = 0;
        if(valuationRequestDetail.getStatus().toString().equalsIgnoreCase(RequestDetailStatus.VALUATING.toString())) {
            for (DiamondValuationAssign dva : valuationRequestDetail.getDiamondValuationAssigns()) {
                if (!dva.isStatus()) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                valuationRequestDetail.setStatus(RequestDetailStatus.VALUATED);
                valuationRequestDetailRepository.save(valuationRequestDetail);
            }
        }

        //save to database
        diamondValuationAssign = diamondValuationAssignRepository.save(diamondValuationAssign);
        return mapToDTO(diamondValuationAssign);
    }

    @Override
    public Response<DiamondValuationAssignDTO> getAllDiamondValuationAssign(int pageNo, int pageSize, String sortBy, String sortDir) {

        //create Pageable intance
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<DiamondValuationAssign> diamondValuationAssigns = diamondValuationAssignRepository.findAll(pageable);
        //get content for page obj

        List<DiamondValuationAssign> diamondValuationAssignList = diamondValuationAssigns.getContent();
        List<DiamondValuationAssignDTO> content =  diamondValuationAssignList.stream()
                .map(diamondValuationAssign -> mapToDTO(diamondValuationAssign)).collect(Collectors.toList());

        Response<DiamondValuationAssignDTO> response = new Response<>();
        response.setContent(content);
        response.setPageNumber(diamondValuationAssigns.getNumber());
        response.setPageSize(diamondValuationAssigns.getSize());
        response.setTotalElement(diamondValuationAssigns.getTotalElements());
        response.setTotalPage(diamondValuationAssigns.getTotalPages());
        response.setLast(diamondValuationAssigns.isLast());

        return response;
    }

    @Override
    public DiamondValuationAssignDTO getDiamondValuationAssignById(long id) {
        DiamondValuationAssign diamondValuationAssign = diamondValuationAssignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diamond valuation assign", "id", id + ""));
        return mapToDTO(diamondValuationAssign);
    }

    private DiamondValuationAssign mapToEntity(DiamondValuationAssignDTO diamondValuationAssignDTO) {
        return mapper.map(diamondValuationAssignDTO, DiamondValuationAssign.class);
    }

    private DiamondValuationAssignDTO mapToDTO(DiamondValuationAssign diamondValuationAssign) {
        return mapper.map(diamondValuationAssign, DiamondValuationAssignDTO.class);
    }

}
