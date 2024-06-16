package br.com.logistreams.application.infrastructure.web.endpoint.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventories")
public class FindInventoryByIdEndpoint {

    @GetMapping("/{id}")
    public String findInventoryById(@PathVariable Integer id) {
        return "Inventory: " + id;
    }
}
