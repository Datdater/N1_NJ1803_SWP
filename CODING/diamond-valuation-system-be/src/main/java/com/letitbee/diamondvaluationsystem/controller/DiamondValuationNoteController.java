package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.entity.DiamondValuationNote;
import com.letitbee.diamondvaluationsystem.payload.DiamondValuationNoteDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.service.DiamondValuationNoteService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diamond-valuation-notes")
public class DiamondValuationNoteController {
    private DiamondValuationNoteService valuationNoteService;

    public DiamondValuationNoteController(DiamondValuationNoteService valuationNoteService) {
        this.valuationNoteService = valuationNoteService;
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'CONSULTANT_STAFF')")
    @PutMapping("{id}")
    public ResponseEntity<DiamondValuationNoteDTO>
    updateDiamondValuationNote(@PathVariable("id") long id,
                               @RequestBody DiamondValuationNoteDTO valuationNoteDTO
    ) {
        return ResponseEntity.ok(valuationNoteService.updateDiamondValuationNote(id, valuationNoteDTO));
    }
    @GetMapping("/search")
    public ResponseEntity<DiamondValuationNoteDTO> getAllDiamondValuationNoteByCertificateId(
            @RequestParam(value = "certificateId",required = false) String certificateId) {
        return ResponseEntity.ok(valuationNoteService.getAllDiamondValuationNoteByCertificateId(certificateId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiamondValuationNoteDTO> getDiamondValuationNoteById(@PathVariable("id") long id) {
        return ResponseEntity.ok(valuationNoteService.getDiamondValuationNoteById(id));
    }
}
