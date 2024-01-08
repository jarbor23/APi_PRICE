package com.test.buy.api.domain.usecase;

public interface UseCase<T, R> {
    R execute(T command);
}
