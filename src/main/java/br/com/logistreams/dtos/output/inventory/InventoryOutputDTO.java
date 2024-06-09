package br.com.logistreams.dtos.output.inventory;

import br.com.logistreams.dtos.output.section.SectionOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryOutputDTO {
    private String name;
    List<SectionOutputDTO> sections;
}
