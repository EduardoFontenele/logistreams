package br.com.logistreams.domain.entity;

import java.util.Set;

public class Inventory {
    private Long id;
    private String name;

    public Inventory(String name) {
        this.name = name;
    }

    public Inventory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Inventory() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
