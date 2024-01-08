package com.test.buy.api.infrastructure.adapter.in.handler.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class ApplicationDateOrderValidator  implements ConstraintValidator<ApplicationDateOrder, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String patter= "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        return Optional.ofNullable(value).map(val-> val.matches(patter)).orElse(false);
    }
}
