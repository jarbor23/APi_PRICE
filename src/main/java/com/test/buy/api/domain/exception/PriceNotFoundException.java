package com.test.buy.api.domain.exception;

public class PriceNotFoundException extends BusinessException{
    public PriceNotFoundException(String exceptionCode, String message) {
        super(exceptionCode, message);
    }
}
