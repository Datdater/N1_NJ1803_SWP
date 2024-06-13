package com.letitbee.diamondvaluationsystem.payload;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String description;
    private String thumbnail;
    private Date publishedDate;
    private Date creationDate;
    private String reference;
}
