package br.com.logistreams.fixtures;

import br.com.logistreams.application.adapters.web.dto.input.inventory.CreateInventoryDTO;
import br.com.logistreams.application.adapters.web.dto.output.Meta;
import br.com.logistreams.application.adapters.web.dto.output.PagedResponse;
import br.com.logistreams.application.adapters.web.dto.output.inventory.ListInventoryDTO;
import br.com.logistreams.application.adapters.web.controller.inventory.ListInventoryEndpoint;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.utils.PagedResponseLinksBuilder;

public class InventoryFixtures {
    private static final PagedResponseLinksBuilder<ListInventoryDTO> pagedResponseLinksBuilder =
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

    public static PagedResponse<ListInventoryDTO> gimmeValidEmptyPagedResponse() {
        PagedResponse<ListInventoryDTO> response = new PagedResponse<>();
        response.setData(new ListInventoryDTO[] {});
        response.setMeta(Meta.builder()
                .totalRecords(0)
                .currentPage(1)
                .totalPages(1)
                .build());
        response.setLinks(pagedResponseLinksBuilder.buildLinks(1, 1, 1));
        return response;
    }
}
