package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.Service;
import com.letitbee.diamondvaluationsystem.entity.ServicePriceList;
import com.letitbee.diamondvaluationsystem.exception.APIException;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.ServicePriceListDTO;
import com.letitbee.diamondvaluationsystem.repository.ServicePriceListRepository;
import com.letitbee.diamondvaluationsystem.repository.ServiceRepository;
import com.letitbee.diamondvaluationsystem.service.ServicePriceListService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicePriceListImpl implements ServicePriceListService {
    private ServicePriceListRepository servicePriceListRepository;
    private ServiceRepository  serviceRepository;
    private ModelMapper mapper;

    public ServicePriceListImpl(ServicePriceListRepository servicePriceListRepository, ServiceRepository serviceRepository, ModelMapper mapper) {
        this.servicePriceListRepository = servicePriceListRepository;
        this.serviceRepository = serviceRepository;
        this.mapper = mapper;
    }


    @Override
    public List<ServicePriceListDTO> getAllServicePriceList() {
        List<ServicePriceList> servicePriceLists = servicePriceListRepository.findAll();
        return servicePriceLists.stream().map(servicePriceList -> mapToDto(servicePriceList)).collect(Collectors.toList());
    }

    @Override
    public ServicePriceListDTO getServicePriceListById(long id) {
        ServicePriceList servicePriceList = servicePriceListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ServicePriceList", "id", id + ""));
        return mapToDto(servicePriceList);
    }

    @Override
    public ServicePriceListDTO createServicePriceList(ServicePriceListDTO servicePriceListDto) {
        if(servicePriceListDto.getMinSize() > servicePriceListDto.getMaxSize()) {
            throw new APIException( HttpStatus.BAD_REQUEST, "Max size must be greater than min size");
        }
        ServicePriceList servicePriceList = mapToEntity(servicePriceListDto);
        return mapToDto(servicePriceListRepository.save(servicePriceList));
    }

    private ServicePriceListDTO mapToDto(ServicePriceList servicePriceList) {
        return mapper.map(servicePriceList, ServicePriceListDTO.class);
    }
    private ServicePriceList mapToEntity(ServicePriceListDTO servicePriceListDto) {
        return mapper.map(servicePriceListDto, ServicePriceList.class);
    }
}
