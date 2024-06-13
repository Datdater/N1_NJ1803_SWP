package com.letitbee.diamondvaluationsystem.payload;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String timestamp;
    private String message;
    private String detail;

}
