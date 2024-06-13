package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.enums.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/v1/field-diamonds")
public class FieldDiamondController {

    @GetMapping("/claritys")
    public Map<String, String> getClarities() {
        Map<String, String> clarities = new HashMap<>();
        for (Clarity clarity : Clarity.values()) {
            clarities.put(clarity.name(), clarity.name());
        }
        return clarities;
    }

    @GetMapping("/colors")
    public Map<String, String> getColors() {
        Map<String, String> colors = new HashMap<>();
        for (Color color : Color.values()) {
            colors.put(color.name(), color.name());
        }
        return colors;
    }

    @GetMapping("/cuts")
    public Map<String, String> getCuts() {
        Map<String, String> cuts = new HashMap<>();
        for (Cut cut : Cut.values()) {
            cuts.put(cut.name(), cut.name());
        }
        return cuts;
    }

    @GetMapping("/diamond-origins")
    public Map<String, String> getDiamondOrigins() {
        Map<String, String> diamondOrigins = new HashMap<>();
        for (DiamondOrigin diamondOrigin : DiamondOrigin.values()) {
            diamondOrigins.put(diamondOrigin.name(), diamondOrigin.name());
        }
        return diamondOrigins;
    }

    @GetMapping("/shapes")
    public Map<String, String> getShapes() {
        Map<String, String> shapes = new HashMap<>();
        for (Shape shape : Shape.values()) {
            shapes.put(shape.name(), shape.name());
        }
        return shapes;
    }

    @GetMapping("/polishs")
    public Map<String, String> getPolishs() {
        Map<String, String> polishs = new HashMap<>();
        for (Polish polish : Polish.values()) {
            polishs.put(polish.name(), polish.name());
        }
        return polishs;
    }

    @GetMapping("/symmetries")
    public Map<String, String> getSymmetries() {
        Map<String, String> symmetries = new HashMap<>();
        for (Symmetry symmetry : Symmetry.values()) {
            symmetries.put(symmetry.name(), symmetry.name());
        }
        return symmetries;
    }

    @GetMapping("/fluorescences")
    public Map<String, String> getFluorescences() {
        Map<String, String> fluorescences = new HashMap<>();
        for (Fluorescence fluorescence : Fluorescence.values()) {
            fluorescences.put(fluorescence.name(), fluorescence.name());
        }
        return fluorescences;
    }

    @GetMapping("/clarity-characteristics")
    public Map<String, String> getClarityCharacteristics() {
        Map<String, String> clarityCharacteristics = new HashMap<>();
        for (ClarityCharacteristic clarityCharacteristic : ClarityCharacteristic.values()) {
            clarityCharacteristics.put(clarityCharacteristic.name(), clarityCharacteristic.name());
        }
        return clarityCharacteristics;
    }

}
