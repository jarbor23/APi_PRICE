package com.test.buy.api.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String errorCode;
    public BusinessException(String exceptionCode, String message){
        super(message);
        this.errorCode = exceptionCode;
    }
}
