package com.letitbee.diamondvaluationsystem.payload;

import com.letitbee.diamondvaluationsystem.entity.DiamondImage;
import com.letitbee.diamondvaluationsystem.entity.ValuationRequest;
import com.letitbee.diamondvaluationsystem.enums.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class DiamondValuationNoteDTO {
    private long id;
    private String certificateId;
    private Date certificateDate;
    private String proportions;
    private ArrayList<String> clarityCharacteristic = new ArrayList<>();
    private String clarityCharacteristicLink;
    private DiamondOrigin diamondOrigin;
    private float caratWeight;
    private Color color;
    private Clarity clarity;
    private Cut cut;
    private Polish polish;
    private Symmetry symmetry;
    private Shape shape;
    private Fluorescence fluorescence;
    private double fairPrice;
    private double minPrice;
    private double maxPrice;
    private Set<DiamondImageDTO> diamondImages = new HashSet<>();
}
