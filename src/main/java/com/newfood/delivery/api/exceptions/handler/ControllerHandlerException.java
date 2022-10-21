package com.newfood.delivery.api.exceptions.handler;

import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.EntityNotFoundException;
import com.newfood.delivery.api.exceptions.EntityInUseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = String.valueOf(ex.getPath());

        ErrorType errorType = ErrorType.MESSAGE_INEXPLICABLE;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        Error error = createErrorBuilder(status, errorType, detail)
                .useMessage(detail)
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorType errorType = ErrorType.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();

        List<Error.Field> fields = bindingResult.getFieldErrors().stream().map(fieldError -> Error.Field.builder()
                        .name(fieldError.getField())
                        .userMessage(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        Error error = createErrorBuilder(status, errorType, detail)
                .useMessage(detail)
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
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
