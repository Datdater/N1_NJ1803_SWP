package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DiamondImageDTO {
    private Long id;
    @NotEmpty(message = "Image cannot be empty")
    private String image;
    private Long diamondValuationNoteID;
}
