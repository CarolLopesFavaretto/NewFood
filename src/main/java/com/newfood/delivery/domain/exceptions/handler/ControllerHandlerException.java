package com.newfood.delivery.domain.exceptions.handler;

import com.newfood.delivery.domain.exceptions.BusinessException;
import com.newfood.delivery.domain.exceptions.EntityInUseException;
import com.newfood.delivery.domain.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> entityInUseException(EntityInUseException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ErrorType errorType = ErrorType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        Error error = createErrorBuilder(status, errorType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessException(BusinessException ex, WebRequest request) {


        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BUSINESS;
        String detail = ex.getMessage();

        Error error = createErrorBuilder(status, errorType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.ENTITY_NOT_FOUND;
        String detail = ex.getMessage();

        Error error = createErrorBuilder(status, errorType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Error.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Error.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        ErrorType errorType = ErrorType.MESSAGE_INEXPLICABLE;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Error error = createErrorBuilder(status, errorType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    private Error.ErrorBuilder createErrorBuilder(HttpStatus status,
                                                  ErrorType problemType, String detail) {

        return Error.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .dateTime(LocalDateTime.now());
    }
}
