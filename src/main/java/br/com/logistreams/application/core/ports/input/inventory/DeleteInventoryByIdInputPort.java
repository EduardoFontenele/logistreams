package br.com.logistreams.application.core.ports.input.inventory;

public interface DeleteInventoryByIdInputPort {
    void execute(Long id);
}
