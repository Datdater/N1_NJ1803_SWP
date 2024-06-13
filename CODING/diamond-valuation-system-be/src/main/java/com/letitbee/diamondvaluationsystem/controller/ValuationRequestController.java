package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestDTO;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestResponse;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestResponseV2;
import com.letitbee.diamondvaluationsystem.service.ValuationRequestService;
import com.letitbee.diamondvaluationsystem.utils.AppConstraint;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("api/v1/valuation-requests")
public class ValuationRequestController {
    private ValuationRequestService valuationRequestService;

    public ValuationRequestController(ValuationRequestService valuationRequestService) {
        this.valuationRequestService = valuationRequestService;
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'CONSULTANT_STAFF')")
    @GetMapping
    public ResponseEntity<Response<ValuationRequestResponse>>
    getAllValuationRequest(@RequestParam(name = "pageNo", defaultValue = AppConstraint.PAGE_NO, required = false) int pageNo,
                           @RequestParam(name = "pageSize", defaultValue = AppConstraint.PAGE_SIZE, required = false) int pageSize,
                           @RequestParam(name = "sortBy", defaultValue = AppConstraint.SORT_BY, required = false) String sortBy,
                           @RequestParam(name = "sortDir", defaultValue = AppConstraint.SORT_DIR, required = false) String sortDir,
                           @RequestParam(name = "startDate", defaultValue = AppConstraint.START_DATE, required = false) String startDate,
                           @RequestParam(name = "endDate", defaultValue = AppConstraint.END_DATE, required = false) String endDate
                           ) {
        Date startDateParse = new Date(startDate);
        Date endDateParse = new Date(endDate);
        return new ResponseEntity<>(valuationRequestService.getAllValuationRequests(pageNo, pageSize, sortBy, sortDir, startDateParse, endDateParse), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'CONSULTANT_STAFF', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<ValuationRequestDTO> getValuationRequest(@PathVariable("id") long id) {
        return ResponseEntity.ok(valuationRequestService.getValuationRequestById(id));
    }

    @PostMapping
    public ResponseEntity<ValuationRequestDTO> createValuationRequest(
            @RequestBody @Valid ValuationRequestDTO valuationRequestDT) {
        return new ResponseEntity<>(valuationRequestService.createValuationRequest(valuationRequestDT), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<ValuationRequestDTO> updateValuationRequest(
            @PathVariable("id") long id,
            @RequestBody @Valid ValuationRequestDTO valuationRequestDT) {
        return ResponseEntity.ok(valuationRequestService.updateValuationRequest(id, valuationRequestDT));
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ValuationRequestDTO> deleteValuationRequest(
            @PathVariable("id") long id) {
        return ResponseEntity.ok(valuationRequestService.deleteValuationRequestById(id));
    }

    @GetMapping("/response")
    public ResponseEntity<Response<ValuationRequestResponseV2>> getValuationRequestResponse(
            @RequestParam(name = "pageNo", defaultValue = AppConstraint.PAGE_NO, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstraint.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstraint.SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstraint.SORT_DIR, required = false) String sortDir,
            @RequestParam(name = "status", required = false) RequestStatus status){
        return new ResponseEntity<>(valuationRequestService.
                getValuationRequestResponse(pageNo, pageSize, sortBy, sortDir,status), HttpStatus.OK);
    }

}
