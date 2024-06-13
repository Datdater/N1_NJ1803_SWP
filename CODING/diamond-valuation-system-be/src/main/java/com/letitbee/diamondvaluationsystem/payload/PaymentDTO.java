package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {
    private long id;
    private Date paytime;
//    @NotEmpty(message = "Amount cannot be empty")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private double amount;
    private String externalTransaction;
    private PaymentMethodDTO paymentMethod;
    private Long valuationRequestID;
}
