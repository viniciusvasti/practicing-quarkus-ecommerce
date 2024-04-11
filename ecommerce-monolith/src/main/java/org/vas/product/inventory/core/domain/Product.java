package org.vas.product.inventory.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity(name = "product_inventory")
public class Product extends PanacheEntity {
    // TODO: set unique constraint
    private String sku;
    private int stockUnits;

    public Product() {
    }

    public Product(String sku, int stockUnits) {
        this.sku = sku;
        this.stockUnits = stockUnits;
    }

    public Product(Long id, String sku, int stockUnits) {
        this.id = id;
        this.sku = sku;
        this.stockUnits = stockUnits;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public int getStockUnits() {
        return stockUnits;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setStockUnits(int stockUnits) {
        this.stockUnits = stockUnits;
    }

    // TODO: get rid of this by using DTOs
    @JsonIgnore
    public boolean isValid() {
        boolean skuIsValid = sku != null && sku.length() == 8;
        boolean stockUnitsIsValid = stockUnits >= 0;

        return skuIsValid && stockUnitsIsValid;
    }
}
