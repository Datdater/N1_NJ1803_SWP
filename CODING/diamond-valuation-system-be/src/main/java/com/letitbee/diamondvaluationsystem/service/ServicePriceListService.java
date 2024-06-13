package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.ServicePriceListDTO;

import java.util.List;

public interface ServicePriceListService {

    List<ServicePriceListDTO> getAllServicePriceList();

    ServicePriceListDTO getServicePriceListById(long id);

    ServicePriceListDTO createServicePriceList(ServicePriceListDTO servicePriceListDto);
}
