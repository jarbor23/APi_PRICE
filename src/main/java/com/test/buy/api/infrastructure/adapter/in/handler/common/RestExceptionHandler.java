package com.test.buy.api.infrastructure.adapter.in.handler.common;

import com.test.buy.api.domain.exception.BusinessException;
import com.test.buy.api.domain.exception.PriceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.test.buy.api.infrastructure.adapter.in.handler.common.Constants.MALFORMED_JSON_REQUEST;
import static com.test.buy.api.infrastructure.adapter.in.handler.common.Constants.OCCURRED_WHILE_CONSUMING_THE_SERVICE;
@EnableWebMvc
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error(MALFORMED_JSON_REQUEST)
                .build();
        return buildResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({NoHandlerFoundException.class})
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String message = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ErrorDTO error = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error(message)
                .build();
        return buildResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error(String.format("code error: %s, %s ",ex.getErrorCode(),ex.getMessage()))
                .build();
        return buildResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({PriceNotFoundException.class})
    protected ResponseEntity<Object> handleHandlerFoundPriceException(PriceNotFoundException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error(String.format("code error: %s, %s ",ex.getErrorCode(),ex.getMessage()))
                .build();
        return buildResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        StringBuilder error = new StringBuilder()
                .append(ex.getMethod())
                .append(" method is not supported for this request. Supported methods are ");
        for (HttpMethod t : Objects.requireNonNull(ex.getSupportedHttpMethods())) {
            error.append(t).append(' ');
        }
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error(error.toString())
                .build();
        return buildResponseEntity(errorDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    protected ResponseEntity<Object> handleMissingServletRequestParameterException(
            Exception ex) {
        ErrorDTO error = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error(ex.getMessage())
                .build();
        return buildResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
   @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalError(
            Exception ex, WebRequest request, HttpServletRequest httpServletRequest) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .error("Internal error : ".concat(ex.getMessage()))
                .build();

        return buildResponseEntity(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseEntity<Object> buildResponseEntity(ErrorDTO error, HttpStatus httpStatus) {
        return new ResponseEntity<>(error, httpStatus);
    }
}
