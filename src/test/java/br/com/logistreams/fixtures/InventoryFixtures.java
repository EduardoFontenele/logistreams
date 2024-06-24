package br.com.logistreams.fixtures;

import br.com.logistreams.application.infrastructure.web.dto.input.inventory.CreateInventoryDTO;
import br.com.logistreams.application.infrastructure.web.dto.output.Meta;
import br.com.logistreams.application.infrastructure.web.dto.output.PagedResponse;
import br.com.logistreams.application.infrastructure.web.dto.output.inventory.InventoryOutputDTO;
import br.com.logistreams.application.infrastructure.web.input.inventory.ListInventoryEndpoint;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.utils.PagedResponseLinksBuilder;

public class InventoryFixtures {
    private static final PagedResponseLinksBuilder<InventoryOutputDTO> pagedResponseLinksBuilder =
            new PagedResponseLinksBuilder<>(ListInventoryEndpoint.class);

    public static CreateInventoryDTO gimmeValidInventoryInputDto() {
        return new CreateInventoryDTO("Camisetas");
    }

    public static CreateInventoryDTO gimmeInvalidInventoryInputDtoEmptyName() {
        return new CreateInventoryDTO("");
    }

    public static Inventory gimmeValidInventoryDomain() {
        Inventory obj = new Inventory();
        obj.setName("Camisetas");

        return obj;
    }

    public static PagedResponse<InventoryOutputDTO> gimmeValidEmptyPagedResponse() {
        PagedResponse<InventoryOutputDTO> response = new PagedResponse<>();
        response.setData(new InventoryOutputDTO[] {});
        response.setMeta(Meta.builder()
                .totalRecords(0)
                .currentPage(1)
                .totalPages(1)
                .build());
        response.setLinks(pagedResponseLinksBuilder.buildLinks(1, 1, 1));
        return response;
    }
}
