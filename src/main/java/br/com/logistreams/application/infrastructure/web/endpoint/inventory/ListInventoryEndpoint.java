package br.com.logistreams.application.infrastructure.web.endpoint.inventory;

import br.com.logistreams.application.infrastructure.web.dto.output.Links;
import br.com.logistreams.application.infrastructure.web.dto.output.Meta;
import br.com.logistreams.application.infrastructure.web.dto.output.PagedResponse;
import br.com.logistreams.application.infrastructure.web.dto.output.inventory.InventoryOutputDTO;
import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.ports.input.inventory.CountInventoriesInputPort;
import br.com.logistreams.domain.ports.input.inventory.ListInventoriesInputPort;
import br.com.logistreams.utils.PagedResponseLinksBuilder;
import br.com.logistreams.utils.ValidatePageParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/inventories")
public class ListInventoryEndpoint {
    private final ListInventoriesInputPort listInventoriesInputPort;
    private final CountInventoriesInputPort countInventoriesInputPort;
    private final InventoryMapper inventoryMapper;

    private final PagedResponseLinksBuilder<InventoryOutputDTO> pagedResponseLinksBuilder =
            new PagedResponseLinksBuilder<>(ListInventoryEndpoint.class);
    private final ValidatePageParams validatePageParams;

    @GetMapping
    public ResponseEntity<PagedResponse<InventoryOutputDTO>> listInventories(
            @RequestParam(value = "page_number", required = false) Integer pageNumber,
            @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        if(pageNumber != null && pageNumber <= 0)
            throw new BusinessLogicException(ErrorsEnum.INVALID_PAGE_NUMBER);
        if((pageSize != null && pageSize <= 0) || (pageSize != null && pageSize > 30))
            throw new BusinessLogicException(ErrorsEnum.INVALID_PAGE_SIZE);

        PagedResponse<InventoryOutputDTO> response = new PagedResponse<>();
        long totalInventories = countInventoriesInputPort.execute();

        if(totalInventories == 0) {
            response.setData(new InventoryOutputDTO[] {});
            response.setMeta(Meta.builder()
                    .totalRecords(0)
                    .currentPage(1)
                    .totalPages(1)
                    .build());
            response.setLinks(pagedResponseLinksBuilder.buildLinks(1, 1, 1));
            return ResponseEntity.ok(response);
        }

        int queryPageSize = validatePageParams.validatePageSize(pageSize);
        int totalPages = (int) Math.ceil((double) totalInventories/queryPageSize);
        int queryPageNumber = validatePageParams.validatePageNumber(pageNumber, totalPages);

        InventoryOutputDTO[] outputDTOS = listInventoriesInputPort.execute(queryPageNumber, queryPageSize)
                .stream()
                .map(inventoryMapper::toInventoryOutputDTO)
                .toArray(InventoryOutputDTO[]::new);
        Meta meta = Meta.builder()
                .currentPage(queryPageNumber)
                .totalPages(totalPages)
                .totalRecords(totalInventories)
                .build();
        Links links = pagedResponseLinksBuilder.buildLinks(queryPageNumber, queryPageSize, totalPages);

        response.setData(outputDTOS);
        response.setMeta(meta);
        response.setLinks(links);

        return ResponseEntity.ok(response);
    }
}
