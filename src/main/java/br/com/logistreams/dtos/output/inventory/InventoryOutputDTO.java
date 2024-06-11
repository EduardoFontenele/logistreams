package br.com.logistreams.dtos.output.inventory;

import br.com.logistreams.dtos.output.OutputDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"id", "name", "sections", "_links"})
public class InventoryOutputDTO extends OutputDTO {
    private int id;
    private String name;
}
