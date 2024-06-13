package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ServicePriceListDTO {
    private long id;
    @Min(value = 4, message = "Size must be greater than or equal to 4")
    private float minSize;
    @Min(value = 4, message = "Max size must be greater than or equal to min size")
    private float maxSize;
    private double initPrice;
    private double unitPrice;
}
