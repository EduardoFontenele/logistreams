package br.com.logistreams.services;

import br.com.logistreams.controllers.InventoryController;
import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.Meta;
import br.com.logistreams.dtos.output.PagedResponse;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.entities.Inventory;
import br.com.logistreams.mappers.InventoryMapper;
import br.com.logistreams.repositories.InventoryRepository;
import br.com.logistreams.utils.PageLinksBuilder;
import br.com.logistreams.utils.PageRequestBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final PageLinksBuilder<Inventory> inventoryPageLinksBuilder = new PageLinksBuilder<>(InventoryController.class);

    @Override
    public void createNewInventory(InventoryInputDTO inventoryInputDTO) {
        Inventory inventory = inventoryMapper.toInventory(inventoryInputDTO);
        inventoryRepository.save(inventory);
    }

    @Override
    public PagedResponse<InventoryOutputDTO> listAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequestBuilder.build(pageNumber, pageSize);
        Page<Inventory> page = inventoryRepository.findAll(pageRequest);

        if(pageNumber != null && pageNumber > page.getTotalPages()) {
            throw new RuntimeException("Ah que num sei que tua página é maior que o total existente");
        }

        List<InventoryOutputDTO> inventoryOutputDtoList = page.getContent()
                .stream()
                .map(inventoryMapper::toInventoryOutputDTO)
                .toList();

        PagedResponse<InventoryOutputDTO> pagedResponse = new PagedResponse<>();
        Meta meta = new Meta();

        meta.setCurrentPage(page.getNumber() + 1);
        meta.setTotalPages(page.getTotalPages());
        meta.setTotalRecords(((int) page.getTotalElements()));

        pagedResponse.setData(inventoryOutputDtoList);
        pagedResponse.setMeta(meta);
        pagedResponse.setLinks(inventoryPageLinksBuilder.buildLinks(page));

        return pagedResponse;
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
