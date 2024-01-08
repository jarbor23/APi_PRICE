package com.test.buy.api.infrastructure.adapter.in.handler.common.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;
import jakarta.validation.Constraint;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ApplicationDateOrderValidator.class)
public @interface ApplicationDateOrder {
    String message() default "The applicationDate must meet the specified criteria. YYYY-mm-DD HH:mm:ss";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
