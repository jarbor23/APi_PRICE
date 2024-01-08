package com.test.buy.api.domain.exception;

public class AmbiguityException extends BusinessException{

    public AmbiguityException(String exceptionCode, String message) {
        super(exceptionCode, message);
    }
}
