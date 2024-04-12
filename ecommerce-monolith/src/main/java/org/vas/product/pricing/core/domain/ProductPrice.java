package org.vas.product.pricing.core.domain;

import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Schema(name = "ProductPrice", description = "A product's price by SKU")
@Entity(name = "product_price")
public class ProductPrice extends PanacheEntity {
    // TODO: set unique constraint
    @Schema(required = true, example = "12345678")
    private String sku;
    @Schema(required = true, example = "100.97")
    private BigDecimal price;

    public ProductPrice() {
    }

    public ProductPrice(String sku, BigDecimal price) {
        this.sku = sku;
        this.price = price;
    }

    public ProductPrice(Long id, String sku, BigDecimal price) {
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
