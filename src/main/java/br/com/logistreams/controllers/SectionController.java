package br.com.logistreams.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SectionController {

    @GetMapping("/v1/inventories/{inventoryId}/sections")
    public ResponseEntity<String> getSections(@PathVariable("inventoryId") Integer inventoryId) {
        return ResponseEntity.ok("Hello sections");
    }
}
