package org.vas.product.pricing.core.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity(name = "product_price")
public class Product extends PanacheEntity {
    // TODO: set unique constraint
    private String sku;
    private BigDecimal price;

    public Product() {
    }

    public Product(String sku, BigDecimal price) {
        this.sku = sku;
        this.price = price;
    }

    public Product(Long id, String sku, BigDecimal price) {
        this.id = id;
        this.sku = sku;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // TODO: get rid of this by using DTOs
    @JsonIgnore
    public boolean isValid() {
        boolean skuIsValid = sku != null && sku.length() == 8;
        boolean priceIsValid = price != null && price.compareTo(BigDecimal.ZERO) > 0;

        return skuIsValid && priceIsValid;
    }
}
