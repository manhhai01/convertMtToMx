package com.hdbank.convertMTtoMXproject.exception;

import com.hdbank.convertMTtoMXproject.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(BaseResponse.builder()
                .status(500)
                .message("Can not convert MT to MX.")
                .data("")
                .build(), HttpStatus.OK);
    }
}

