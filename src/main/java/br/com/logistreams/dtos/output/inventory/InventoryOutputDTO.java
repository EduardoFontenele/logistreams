package br.com.logistreams.dtos.output.inventory;

import br.com.logistreams.dtos.output.section.SectionOutputDTO;
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
public class InventoryOutputDTO {
    private int id;
    private String name;
    Set<SectionOutputDTO> sections;
}
