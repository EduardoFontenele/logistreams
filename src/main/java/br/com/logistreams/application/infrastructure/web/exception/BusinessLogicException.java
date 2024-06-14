package br.com.logistreams.application.infrastructure.web.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessLogicException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public BusinessLogicException(ErrorsEnum errorsEnum) {
        this.httpStatus = errorsEnum.getHttpStatus();
        this.errorMessage = errorsEnum.getErrorMessage();
    }

    public BusinessLogicException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
