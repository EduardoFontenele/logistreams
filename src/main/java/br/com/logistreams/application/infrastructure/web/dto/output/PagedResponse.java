package br.com.logistreams.application.infrastructure.web.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedResponse<T> {
    private T[] data;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Links links;
    private Meta meta;
}
