package br.com.logistreams.application.infrastructure.web.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Links {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String self;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String first;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String last;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String previous;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String next;
}
