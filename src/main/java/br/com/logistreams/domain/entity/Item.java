package br.com.logistreams.domain.entity;

import java.math.BigDecimal;

public class Item {
    private Integer id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;

    public Item(Integer id, String name, String sku, BigDecimal price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Item() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSku() {
        return this.sku;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getStockQuantity() {
        return this.stockQuantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
