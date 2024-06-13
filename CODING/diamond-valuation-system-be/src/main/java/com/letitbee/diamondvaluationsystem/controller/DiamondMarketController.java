package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.entity.DiamondMarket;
import com.letitbee.diamondvaluationsystem.enums.*;
import com.letitbee.diamondvaluationsystem.payload.DiamondMarketDTO;
import com.letitbee.diamondvaluationsystem.payload.DiamondPriceListDTO;
import com.letitbee.diamondvaluationsystem.service.DiamondMarketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diamond-market")
public class DiamondMarketController {
    private DiamondMarketService diamondMarketService;

    public DiamondMarketController(DiamondMarketService diamondMarketService) {
        this.diamondMarketService = diamondMarketService;
    }

    @PostMapping
    public ResponseEntity<DiamondMarketDTO> createDiamondMarket(@RequestBody DiamondMarketDTO diamondMarketDTO) {
        return new ResponseEntity<>(diamondMarketService.createDiamondMarket(diamondMarketDTO), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DiamondMarketDTO>> getAllDiamondMarket(
            @RequestParam(value = "diamondOrigin") DiamondOrigin diamondOrigin,
            @RequestParam(value = "caratWeight") float caratWeight,
            @RequestParam(value = "color") Color color,
            @RequestParam(value = "clarity") Clarity clarity,
            @RequestParam(value = "cut") Cut cut,
            @RequestParam(value = "polish") Polish polish,
            @RequestParam(value = "symmetry") Symmetry symmetry,
            @RequestParam(value = "shape") Shape shape,
            @RequestParam(value = "fluorescence") Fluorescence fluorescence
    ){
        return new ResponseEntity<>(diamondMarketService.getAllDiamondMarket(
                diamondOrigin,
                caratWeight,
                color,
                clarity,
                cut,
                polish,
                symmetry,
                shape,
                fluorescence), HttpStatus.OK);
    }

    @GetMapping("/price-list")
    public ResponseEntity<DiamondPriceListDTO> createDiamondPriceList(
            @RequestParam(value = "diamondOrigin") DiamondOrigin diamondOrigin,
            @RequestParam(value = "caratWeight") float caratWeight,
            @RequestParam(value = "color") Color color,
            @RequestParam(value = "clarity") Clarity clarity,
            @RequestParam(value = "cut") Cut cut,
            @RequestParam(value = "polish") Polish polish,
            @RequestParam(value = "symmetry") Symmetry symmetry,
            @RequestParam(value = "shape") Shape shape,
            @RequestParam(value = "fluorescence") Fluorescence fluorescence
    ){
        return new ResponseEntity<>(diamondMarketService.createDiamondPriceList(
                diamondOrigin,
                caratWeight,
                color,
                clarity,
                cut,
                polish,
                symmetry,
                shape,
                fluorescence), HttpStatus.OK);
    }
}
