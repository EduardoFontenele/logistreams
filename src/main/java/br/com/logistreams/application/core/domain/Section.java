package br.com.logistreams.application.core.domain;


import java.util.HashSet;
import java.util.Set;

public class Section {
    private Long id;
    private String name;
    private Set<Item> items = new HashSet<>();

    public Section(Long id, String name, Set<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Section() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
