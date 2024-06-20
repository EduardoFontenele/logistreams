package br.com.logistreams.domain.ports.input.inventory;

import br.com.logistreams.domain.entity.Inventory;

import java.util.List;

public interface ListInventoriesInputPort {
    List<Inventory> execute(int pageNumber, int pageSize);
}
