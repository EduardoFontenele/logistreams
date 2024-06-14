package br.com.logistreams.application.infrastructure.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse {
    public Map<String, String> fields;

    public ValidationErrorResponse(HttpStatus httpStatus, String errorMessage, Map<String,String> fields) {
        super(httpStatus, errorMessage);
        this.fields = fields;
    }


}
