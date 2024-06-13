package com.letitbee.diamondvaluationsystem.payload;

import lombok.Data;

import java.util.Date;

@Data
public class DiamondValuationAssignDTO {
    private Long id;
    private Long valuationRequestDetailId;
    private Date creationDate;
    private String comment;
    private String commentDetail;
    private double valuationPrice;
    private boolean status;
    private Long staffId;
}
