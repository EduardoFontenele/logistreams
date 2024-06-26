package br.com.logistreams.application.adapters.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorsEnum {
    INVALID_FIELDS(HttpStatus.BAD_REQUEST, "Some fields failed on validation", 1001),
    RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Resource already exists", 1002),
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "Page number cannot be zero nor negative", 1003),
    INVALID_PAGE_SIZE(HttpStatus.BAD_REQUEST, "Page size cannot be zero, negative or greater than 30", 1004),
    PAGE_NUMBER_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "Page number is is out of range compared to total pages available", 1005),
    INVALID_ID(HttpStatus.BAD_REQUEST, "ID is invalid, can't be null, empty or negative", 1006),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found", 1007);

    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final int codeError;
}
