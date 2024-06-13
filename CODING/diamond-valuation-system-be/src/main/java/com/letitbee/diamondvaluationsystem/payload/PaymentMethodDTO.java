package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class PaymentMethodDTO {
    private long id;
    @Min(value = 1, message = "Payment method name id must be greater than or equal to 1")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
