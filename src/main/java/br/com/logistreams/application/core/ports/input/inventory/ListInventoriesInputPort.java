package br.com.logistreams.application.core.ports.input.inventory;

import br.com.logistreams.application.core.domain.Inventory;

import java.util.List;

public interface ListInventoriesInputPort {
    List<Inventory> execute(int pageNumber, int pageSize);
}
