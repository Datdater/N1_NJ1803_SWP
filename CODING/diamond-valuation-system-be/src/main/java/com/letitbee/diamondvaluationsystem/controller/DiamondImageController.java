package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.DiamondImageDTO;
import com.letitbee.diamondvaluationsystem.service.DiamondImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diamond-images")
public class DiamondImageController {
    private DiamondImageService diamondImageService;

    public DiamondImageController(DiamondImageService diamondImageService) {
        this.diamondImageService = diamondImageService;
    }

    @PostMapping
    public ResponseEntity<DiamondImageDTO> createDiamondImage(@RequestBody @Valid DiamondImageDTO diamondImageDTO) {
        return new ResponseEntity<>(diamondImageService.createDiamondImage(diamondImageDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiamondImageDTO> updateDiamondImage(@PathVariable("id") long id,
                                                              @RequestBody @Valid DiamondImageDTO diamondImageDTO) {
        return ResponseEntity.ok(diamondImageService.updateDiamondImage(id, diamondImageDTO));
    }

}
