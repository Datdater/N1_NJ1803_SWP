package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.ServiceDTO;
import com.letitbee.diamondvaluationsystem.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllService() {
        return new ResponseEntity<>(serviceService.getAllService(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(serviceService.getServiceById(id), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody @Valid ServiceDTO serviceDto) {
        return new ResponseEntity<>(serviceService.createService(serviceDto), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@RequestBody @Valid ServiceDTO serviceDto, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(serviceService.updateService(serviceDto, id), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable(name = "id") long id) {
        serviceService.deleteServiceById(id);
    }
}
