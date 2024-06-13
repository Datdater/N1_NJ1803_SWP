package com.letitbee.diamondvaluationsystem.payload;

import com.letitbee.diamondvaluationsystem.enums.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
public class DiamondMarketDTO {
    private Long id;
    private String diamondImage;
    private Date creationDate;
    private DiamondOrigin diamondOrigin;
    private float caratWeight;
    private Color color;
    private Clarity clarity;
    private Cut cut;
    private Polish polish;
    private Symmetry symmetry;
    private Shape shape;
    private Fluorescence fluorescence;
    private double cutScore;
    private double price;
    private Long supplierId;
}
