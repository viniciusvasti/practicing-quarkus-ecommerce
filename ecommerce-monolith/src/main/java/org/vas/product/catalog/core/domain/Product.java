package org.vas.product.catalog.core.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Schema(name = "Product", description = "A product in the store catalog")
@Entity
public class Product extends PanacheEntity {
    // TODO: set unique constraint
    @Schema(required = true, example = "12345678")
    private String sku;
    @Schema(required = true, example = "Product Name")
    private String name;
    @Schema(required = true, example = "Product Description")
    private String description;
    @ManyToOne
    private ProductCategory category;

    public Product() {
    }

    public Product(String sku, String name, String description, ProductCategory category) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Product(Long id, String sku, String name, String description, ProductCategory category) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    // TODO: get rid of this by using DTOs
    @JsonIgnore
    public boolean isValid() {
        boolean skuIsValid = sku != null && sku.length() == 8;
        boolean nameIsValid = name != null && name.length() >= 3 && name.length() <= 255;
        boolean descriptionIsValid = description != null && description.length() >= 3 && description.length() <= 1000;
        boolean categoryIsValid = category != null;

        return skuIsValid && nameIsValid && descriptionIsValid && categoryIsValid;
    }
}
