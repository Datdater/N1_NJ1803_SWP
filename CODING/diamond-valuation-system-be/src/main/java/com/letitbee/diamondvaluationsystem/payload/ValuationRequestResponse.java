package com.letitbee.diamondvaluationsystem.payload;

import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ValuationRequestResponse {
    private long id;
    private Date creationDate;
    private ServiceDTO service;
    private CustomerDTO customer;
    private RequestStatus status;
    private int diamondAmount;
}
