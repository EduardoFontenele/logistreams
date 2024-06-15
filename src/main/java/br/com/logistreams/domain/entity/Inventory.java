package br.com.logistreams.domain.entity;

import java.util.Set;

public class Inventory {
    private Long id;

    public Inventory(String name) {
        this.name = name;
    }

    private String name;
    private Set<Section> sections;

    public Inventory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Inventory(Long id, String name, Set<Section> sections) {
        this.id = id;
        this.name = name;
        this.sections = sections;
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

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }
}
