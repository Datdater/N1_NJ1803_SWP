package com.letitbee.diamondvaluationsystem.payload;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class Response<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPage;
    private boolean last;

}
