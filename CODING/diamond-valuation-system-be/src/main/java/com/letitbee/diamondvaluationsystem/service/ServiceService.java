package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.ServiceDTO;

import java.util.List;

public interface ServiceService {
    List<ServiceDTO> getAllService();
    ServiceDTO getServiceById(long id);
    ServiceDTO createService(ServiceDTO serviceDto);
    ServiceDTO updateService(ServiceDTO serviceDto, long id);
    void deleteServiceById(long id);

}
