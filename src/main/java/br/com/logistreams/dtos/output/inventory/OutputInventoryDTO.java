package br.com.logistreams.dtos.output.inventory;

import br.com.logistreams.dtos.output.section.OutputSectionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputInventoryDTO {
    List<OutputSectionDTO> sections;
}
