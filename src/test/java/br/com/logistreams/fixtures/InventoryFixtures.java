package br.com.logistreams.fixtures;

import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.domain.entity.Inventory;

public class InventoryFixtures {

    public static InventoryInputDTO gimmeValidInventoryInputDto() {
        return new InventoryInputDTO("Camisetas");
    }

    public static InventoryInputDTO gimmeInvalidInventoryInputDtoEmptyName() {
        return new InventoryInputDTO("");
    }

    public static Inventory gimmeValidInventoryDomain() {
        Inventory obj = new Inventory();
        obj.setName("Camisetas");

        return obj;
    }
}
