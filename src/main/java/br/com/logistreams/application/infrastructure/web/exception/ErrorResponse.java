package br.com.logistreams.application.infrastructure.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    public String httpStatus;
    public String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Map<String, String> fields;
}
