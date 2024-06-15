package br.com.logistreams.domain.ports.input.inventory;

public interface CreateInventoryInputPort {
    void execute(String inventoryName);
}
