package br.com.logistreams.domain.entity;

import java.util.List;

public class Inventory {
    private Long id;
    private String name;
    private List<Section> sections;

    public Inventory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Inventory(Long id, String name, List<Section> sections) {
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
