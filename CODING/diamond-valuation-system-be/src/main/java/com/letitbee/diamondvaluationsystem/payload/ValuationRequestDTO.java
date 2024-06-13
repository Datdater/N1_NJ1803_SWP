package com.letitbee.diamondvaluationsystem.payload;

import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ValuationRequestDTO {
    private long id;
    private Date creationDate;
    private Date receiptDate;
    private Date returnDate;
    @Min(value = 1, message = "Diamond amount must be greater than 0")
    private int diamondAmount;
    private double totalServicePrice;
    private String receiptLink;
    private String returnLink;
    private String resultLink;
    private String sealingRecordLink;
    private String feedback;
    private String cancelReason;
    private RequestStatus status;
    private Set<PaymentDTO> payment = new HashSet<>();
    private Set<ValuationRequestDetailDTO> valuationRequestDetails = new HashSet<>();
    private ServiceDTO service;
    private Long customerID;
    private Long staffID;
}
