package com.letitbee.diamondvaluationsystem.payload;

import com.letitbee.diamondvaluationsystem.enums.*;
import lombok.Data;

import java.util.Date;

@Data
public class DiamondPriceListDTO {
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
    private double pricePerCarat;
}
