package br.com.logistreams.services;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.entities.Inventory;
import br.com.logistreams.mappers.InventoryMapper;
import br.com.logistreams.repositories.InventoryRepository;
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

    @Override
    public void createNewInventory(InventoryInputDTO inventoryInputDTO) {
        Inventory inventory = inventoryMapper.toInventory(inventoryInputDTO);
        inventoryRepository.save(inventory);
    }

    @Override
    public List<InventoryOutputDTO> listAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        List<Inventory> inventoryList = inventoryRepository.findAll(pageRequest).getContent();

        return inventoryMapper.toInventoryOutputDTOList(inventoryList);
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
