package com.letitbee.diamondvaluationsystem.payload;

import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ValuationRequestResponseV2 {
    private long id;
    private Date creationDate;
    private String serviceName;
    private String customerFirstName;
    private String customerLastName;
    private RequestStatus status;
    private int diamondAmount;
}
