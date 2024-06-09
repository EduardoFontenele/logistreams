package br.com.logistreams.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {


    @GetMapping
    public ResponseEntity<String> listInventories() {
        return ResponseEntity.ok("Sexo anal");
    }
}
