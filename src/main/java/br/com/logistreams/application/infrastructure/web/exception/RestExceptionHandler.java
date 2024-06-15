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
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        ErrorResponse validationErrorResponse = new ErrorResponse();
        validationErrorResponse.setHttpStatus(ErrorsEnum.INVALID_FIELDS.getHttpStatus().getReasonPhrase());
        validationErrorResponse.setErrorMessage(ErrorsEnum.INVALID_FIELDS.getErrorMessage());
        Map<String, String> validationErrors = new HashMap<>();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        validationErrorResponse.setFields(validationErrors);

        return ResponseEntity.status(ErrorsEnum.INVALID_FIELDS.getHttpStatus().value()).body(validationErrorResponse);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorResponse> handle(BusinessLogicException ex) {
        ErrorResponse validationErrorResponse = ErrorResponse.builder()
                .httpStatus(ErrorsEnum.RESOURCE_ALREADY_EXISTS.getHttpStatus().getReasonPhrase())
                .errorMessage(ErrorsEnum.RESOURCE_ALREADY_EXISTS.getErrorMessage())
                .build();

        return ResponseEntity.status(ErrorsEnum.RESOURCE_ALREADY_EXISTS.getHttpStatus().value()).body(validationErrorResponse);
    }
}
