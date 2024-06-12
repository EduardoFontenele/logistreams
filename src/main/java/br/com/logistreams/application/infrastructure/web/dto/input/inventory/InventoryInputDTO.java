package br.com.logistreams.application.infrastructure.web.dto.input.inventory;

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
public class InventoryInputDTO {
    private String name;
}