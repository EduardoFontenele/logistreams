package br.com.logistreams.controllers;

import br.com.logistreams.dtos.output.inventory.OutputInventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {


    @GetMapping
    public ResponseEntity<List<OutputInventoryDTO>> listInventories() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
