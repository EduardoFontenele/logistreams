package br.com.logistreams.application.infrastructure.web.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class LinkedOutputDTO {
    private Map<String, Object> _links;
}
