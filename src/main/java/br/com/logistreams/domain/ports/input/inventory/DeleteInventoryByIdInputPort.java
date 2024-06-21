package br.com.logistreams.domain.ports.input.inventory;

public interface DeleteInventoryByIdInputPort {
    void execute(Long id);
}
