package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.DiamondValuationAssignDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.service.DiamondValuationAssignService;
import com.letitbee.diamondvaluationsystem.utils.AppConstraint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/diamond-valuation-assigns")
public class DiamondValuationAssignController {
    private DiamondValuationAssignService diamondValuationAssignService;

    public DiamondValuationAssignController(DiamondValuationAssignService diamondValuationAssignService) {
        this.diamondValuationAssignService = diamondValuationAssignService;
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @PostMapping
    public ResponseEntity<DiamondValuationAssignDTO> createDiamondValuationAssign
            (@RequestBody DiamondValuationAssignDTO diamondValuationAssignDTO) {
        return new ResponseEntity<>(diamondValuationAssignService.createDiamondValuationAssign(diamondValuationAssignDTO),
                HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'VALUATION_STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<DiamondValuationAssignDTO> updateDiamondValuationAssign
            (@PathVariable("id") long id,
             @RequestBody DiamondValuationAssignDTO diamondValuationAssignDTO) {
        return ResponseEntity.ok(diamondValuationAssignService.updateDiamondValuationAssign(id, diamondValuationAssignDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiamondValuationAssignDTO> getDiamondValuationAssignById(@PathVariable("id") long id) {
        return ResponseEntity.ok(diamondValuationAssignService.getDiamondValuationAssignById(id));
    }

    @GetMapping
    public ResponseEntity<Response<DiamondValuationAssignDTO>> getAllDiamondValuationAssign(
            @RequestParam(value = "pageNo", defaultValue = AppConstraint.PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstraint.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstraint.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstraint.SORT_DIR, required = false) String sortDir) {
        return ResponseEntity.ok(diamondValuationAssignService.getAllDiamondValuationAssign(pageNo, pageSize, sortBy, sortDir));
    }

}
