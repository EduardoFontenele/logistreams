package br.com.logistreams.utils;

import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import java.util.Map;

@RequiredArgsConstructor
public class LoggerService {
    private final Logger logger;

    public void conflictError() {
        String message = String.format("[ErrorCode: %d] - %s",
                ErrorsEnum.RESOURCE_ALREADY_EXISTS.getCodeError(),
                ErrorsEnum.RESOURCE_ALREADY_EXISTS.getErrorMessage()
        );
        logger.error(message);
    }

    public void validationError(ErrorsEnum errorsEnum, Map<String, String> validationErrors) {
        String message = String.format("[ErrorCode: %d] - %s: %s",
                errorsEnum.getCodeError(),
                errorsEnum.getErrorMessage(),
                buildValidationErrorString(validationErrors)
        );
        logger.error(message);
    }

    public void validationError(ErrorsEnum errorsEnum) {
        String message = String.format("[ErrorCode: %d] - %s",
                errorsEnum.getCodeError(),
                errorsEnum.getErrorMessage()
        );
        logger.error(message);
    }

    private String buildValidationErrorString(Map<String, String> validationErrors) {
        StringBuilder sb = new StringBuilder();
        validationErrors.forEach((field, error) -> {
            if(validationErrors.size() > 1) {
                sb.append(String.format("%s - %s |", field, error));
            } else {
                sb.append(String.format("%s - %s", field, error));
            }
        });
        return sb.toString();
    }
}
