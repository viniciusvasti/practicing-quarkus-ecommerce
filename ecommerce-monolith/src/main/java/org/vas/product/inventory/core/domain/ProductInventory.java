package org.vas.product.inventory.core.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Schema(name = "ProductInventory", description = "A product in the inventory holding the stock units")
@Entity(name = "product_inventory")
public class ProductInventory extends PanacheEntity {
    // TODO: set unique constraint
    @Schema(required = true, example = "12345678")
    private String sku;
    @Schema(required = true, example = "100")
    private int stockUnits;

    public ProductInventory() {
    }

    public ProductInventory(String sku, int stockUnits) {
        this.sku = sku;
        this.stockUnits = stockUnits;
    }

    public ProductInventory(Long id, String sku, int stockUnits) {
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

    public void decreaseStockUnits(int quantity) {
        this.stockUnits -= quantity;
    }
}
