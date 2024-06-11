package br.com.logistreams.services;

import br.com.logistreams.controllers.InventoryController;
import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.Links;
import br.com.logistreams.dtos.output.Meta;
import br.com.logistreams.dtos.output.PagedResponse;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.entities.Inventory;
import br.com.logistreams.mappers.InventoryMapper;
import br.com.logistreams.repositories.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 5;

    @Override
    public void createNewInventory(InventoryInputDTO inventoryInputDTO) {
        Inventory inventory = inventoryMapper.toInventory(inventoryInputDTO);
        inventoryRepository.save(inventory);
    }

    @Override
    public PagedResponse<InventoryOutputDTO> listAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Inventory> page = inventoryRepository.findAll(pageRequest);

        if(pageNumber > page.getTotalPages()) {
            throw new RuntimeException("Ah que num sei que tua página é maior que o total existente");
        }

        List<InventoryOutputDTO> inventoryOutputDtoList = page.getContent()
                .stream()
                .map(inventoryMapper::toInventoryOutputDTO)
                .toList();

        PagedResponse<InventoryOutputDTO> pagedResponse = new PagedResponse<>();
        Meta meta = new Meta();

        meta.setTotalPages(page.getTotalPages());
        meta.setTotalRecords(((int) page.getTotalElements()));

        pagedResponse.setData(inventoryOutputDtoList);
        pagedResponse.setMeta(meta);
        pagedResponse.setLinks(buildLinks(page));

        return pagedResponse;
    }

    private Links buildLinks(Page<Inventory> page) {
        Links links = new Links();
        String pageNumber = "page_number";
        String pageSize = "page_size";

        links.setSelf(WebMvcLinkBuilder.linkTo(InventoryController.class)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, page.getNumber() + 1)
                .queryParam(pageSize, page.getSize())
                .toUriString());

        links.setFirst(WebMvcLinkBuilder.linkTo(InventoryController.class)
                .toUriComponentsBuilder()
                .queryParam(pageNumber,  1)
                .queryParam(pageSize, page.getSize())
                .toUriString());
        
        links.setLast(WebMvcLinkBuilder.linkTo(InventoryController.class)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, page.getTotalPages())
                .queryParam(pageSize, page.getSize())
                .toUriString());

        if(page.getNumber() != 0) {
            links.setPrevious(WebMvcLinkBuilder.linkTo(InventoryController.class)
                    .toUriComponentsBuilder()
                    .queryParam(pageNumber,  page.getNumber())
                    .queryParam(pageSize, page.getSize())
                    .toUriString());
        }

        if(page.getNumber() < page.getTotalPages() - 1) {
            links.setNext(WebMvcLinkBuilder.linkTo(InventoryController.class)
                    .toUriComponentsBuilder()
                    .queryParam(pageNumber,  page.getNumber() + 2)
                    .queryParam(pageSize, page.getSize())
                    .toUriString());
        }

        return links;
    }

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber = 0;
        int queryPageSize = 0;
        
        if(pageNumber == null) {
            queryPageNumber = DEFAULT_PAGE_NUMBER;
        } else if(pageNumber <= 0) {
            throw new IllegalStateException("Page number can't be negative");
        } else {
            queryPageNumber = pageNumber;
        }
        
        if(pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else if(pageSize <= 0 || pageSize >= 25) {
            throw new IllegalStateException("Page number can't be greater than 25 nor lower than 0");
        } else {
            queryPageSize = pageSize;
        }

        return PageRequest.of(queryPageNumber - 1, queryPageSize);
    }

    @Override
    public void deleteById(int id) {
        if(inventoryRepository.findById(id).isPresent()) {
            inventoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Inventory not found");
        }
    }

    @Override
    public InventoryOutputDTO findById(int id) {
        Optional<Inventory> queriedInventory = inventoryRepository.findById(id);

        return queriedInventory.map(inventoryMapper::toInventoryOutputDTO)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    }

    @Override
    public InventoryOutputDTO updateInventory(int id, InventoryInputDTO inventoryInputDTO) {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        if (inventoryInputDTO.getName() != null) {
            existingInventory.setName(inventoryInputDTO.getName());
        }

        inventoryRepository.save(existingInventory);

        return inventoryMapper.toInventoryOutputDTO(existingInventory);
    }
}
