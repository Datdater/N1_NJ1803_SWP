package com.letitbee.diamondvaluationsystem.exception;

import com.letitbee.diamondvaluationsystem.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFound(ResourceNotFoundException resourceNotFoundException,
                                                              WebRequest webRequest) {
        ErrorDetail detail = new ErrorDetail();
        detail.setTimestamp((new Date()).toString());
        detail.setMessage(resourceNotFoundException.getMessage());
        detail.setDetail(webRequest.getDescription(false));
        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetail> handleBlogAPIException(APIException exception,
                                                               WebRequest webRequest){
        ErrorDetail errorDetails = new ErrorDetail(new Date().toString(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception exception,
                                                              WebRequest webRequest) {
        ErrorDetail detail = new ErrorDetail();
        detail.setTimestamp((new Date()).toString());
        detail.setMessage(exception.getMessage());
        detail.setDetail(webRequest.getDescription(false));
        return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException exception,
//                                                                   WebRequest webRequest){
//        ErrorDetail errorDetails = new ErrorDetail((new Date()).toString(), exception.getMessage(),
//                webRequest.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDetail> handleBindException(BindException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setTimestamp(new Date().toString());
        errorDetail.setMessage(e.getMessage());
        errorDetail.setDetail(request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }



}
