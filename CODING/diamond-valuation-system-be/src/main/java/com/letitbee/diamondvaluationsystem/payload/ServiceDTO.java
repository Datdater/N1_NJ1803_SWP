package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.Set;

@Data
public class ServiceDTO {
     private long id;
     @NotEmpty(message = "Name cannot be empty")
     private String name;
     @NotEmpty(message = "Description cannot be empty")
     private String description;
     @Min(value = 1, message = "Period must be greater than 0")
     private int period;
     private Set<ServicePriceListDTO> servicePriceLists;
}
