package br.com.logistreams.dtos.output;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class OutputDTO {
    private Map<String, Object> _links;
}
