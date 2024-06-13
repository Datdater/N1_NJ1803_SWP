package com.letitbee.diamondvaluationsystem.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerDTO {
      private Long id;
      @NotEmpty(message = "First name cannot be empty")
      @Size(min = 2, max = 24, message = "First name must be between 2 and 24 characters")
      private String firstName;
      @NotEmpty(message = "Last name cannot be empty")
      @Size(min = 2, max = 24, message = "Last name must be between 2 and 24 characters")
      private String lastName;
      @Email(message = "Invalid email address")
      private String email;
      @NotEmpty(message = "Phone number cannot be empty")
      @Size(min = 10, max = 10, message = "Invalid phone number")
      private String phone;
      private String address;
      private String avatar;
      @NotEmpty(message = "Identity document cannot be empty")
      @Size(min = 12, max = 12, message = "Invalid identity document")
      private String identityDocument;
      private Set<Long> valuationRequestIDSet = new HashSet<>();
      private AccountResponse account;
}
