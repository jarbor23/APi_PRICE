package com.test.buy.api.infrastructure.adapter.in.handler.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDTO {
    private String message;
    private String error;
}
