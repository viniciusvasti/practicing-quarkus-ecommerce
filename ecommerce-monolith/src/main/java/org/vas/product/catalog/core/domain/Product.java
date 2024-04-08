package org.vas.product.catalog.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends PanacheEntity {
    private String name;
    private String description;
    @ManyToOne
    private ProductCategory category;

    public Product() {
    }

    public Product(String name, String description, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Product(Long id, String name, String description, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
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
        boolean nameIsValid = name != null && !name.isBlank() && name.length() >= 3 && name.length() <= 255;
        boolean descriptionIsValid = description != null && !description.isBlank() && description.length() >= 3 && description.length() <= 1000;
        boolean categoryIsValid = category != null;

        return nameIsValid && descriptionIsValid && categoryIsValid;
    }
}
