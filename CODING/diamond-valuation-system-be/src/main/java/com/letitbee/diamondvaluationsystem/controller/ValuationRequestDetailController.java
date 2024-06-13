package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestDetailDTO;
import com.letitbee.diamondvaluationsystem.service.ValuationRequestDetailService;
import com.letitbee.diamondvaluationsystem.utils.AppConstraint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/valuation-request-details")
public class ValuationRequestDetailController {
    private ValuationRequestDetailService valuationRequestDetailService;

    public ValuationRequestDetailController(ValuationRequestDetailService valuationRequestDetailService) {
        this.valuationRequestDetailService = valuationRequestDetailService;
    }

    @GetMapping
    public ResponseEntity<Response<ValuationRequestDetailDTO>> getAllValuationRequestDetail(@RequestParam(name = "pageNo", defaultValue = AppConstraint.PAGE_NO, required = false) int pageNo,
                                                                                   @RequestParam(name = "pageSize", defaultValue = AppConstraint.PAGE_SIZE, required = false) int pageSize,
                                                                                   @RequestParam(name = "sortBy", defaultValue = AppConstraint.SORT_BY, required = false) String sortBy,
                                                                                   @RequestParam(name = "sortDir", defaultValue = AppConstraint.SORT_DIR, required = false) String sortDir) {
        return new ResponseEntity<>(valuationRequestDetailService.getAllValuationRequestDetail(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValuationRequestDetailDTO> getValuationRequestDetailById(@PathVariable long id) {
        return ResponseEntity.ok(valuationRequestDetailService.getValuationRequestDetailById(id));
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'VALUATION_STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<ValuationRequestDetailDTO> updateValuationRequestDetail(
            @PathVariable long id,
            @RequestBody ValuationRequestDetailDTO valuationRequestDetailDTO) {

        return ResponseEntity.ok(valuationRequestDetailService.updateValuationRequestDetail(id, valuationRequestDetailDTO));
    }
}
