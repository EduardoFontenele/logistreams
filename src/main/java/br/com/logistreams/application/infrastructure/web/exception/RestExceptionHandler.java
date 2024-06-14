package br.com.logistreams.application.infrastructure.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handle(MethodArgumentNotValidException ex) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        validationErrorResponse.setHttpStatus(ErrorsEnum.INVALID_FIELDS.getHttpStatus());
        validationErrorResponse.setErrorMessage(ErrorsEnum.INVALID_FIELDS.getErrorMessage());
        Map<String, String> validationErrors = new HashMap<>();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        validationErrorResponse.setFields(validationErrors);

        return ResponseEntity.status(validationErrorResponse.httpStatus.value()).body(validationErrorResponse);
    }
}
