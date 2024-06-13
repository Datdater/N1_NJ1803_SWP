package com.letitbee.diamondvaluationsystem.payload;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private String creationDate;
    private String message;
    private Boolean isRead;
    private Long accountId;
}
