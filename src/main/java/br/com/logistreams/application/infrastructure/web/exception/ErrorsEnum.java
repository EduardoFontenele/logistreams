package br.com.logistreams.application.infrastructure.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorsEnum {
    INVALID_FIELDS(HttpStatus.BAD_REQUEST, "Some fields failed on validation", 1001),
    RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Resource already exists", 1002);

    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final int codeError;
}
