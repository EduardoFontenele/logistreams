package br.com.logistreams.application.adapters.web.dto.output.inventory;

import br.com.logistreams.application.adapters.web.dto.output.LinkedOutputDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"id", "name", "_links"})
public class ListInventoryDTO extends LinkedOutputDTO {
    private Long id;
    private String name;
}
