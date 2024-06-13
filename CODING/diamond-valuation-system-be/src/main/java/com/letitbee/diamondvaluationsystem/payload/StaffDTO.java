package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class StaffDTO {
    private long id;
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 24, message = "First name must be between 2 and 24 characters")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 24, message = "Last name must be between 2 and 24 characters")
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;
    @Size(min = 10, max = 10, message = "Invalid phone number")
    private String phone;
    @Min(value = 0, message = "Experience must be greater than or equal to 0")
    private int experience;
    private String certificateLink;
    private Set<Long> valuationRequestIDSet;
    private Set<Long> diamondValuationAssignIDSet;
    private AccountResponse account;
    private int countProject = 0;
    private int currentTotalProject;
}
