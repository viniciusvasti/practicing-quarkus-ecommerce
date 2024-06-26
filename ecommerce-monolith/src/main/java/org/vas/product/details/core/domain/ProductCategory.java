package org.vas.product.details.core.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

/**
 * The entity makes use of Panache, a layer on top of Hibernate ORM
 */
@Schema(name = "ProductCategory", description = "A category of products in the store catalog")
@Entity
public class ProductCategory extends PanacheEntity {
    @Schema(required = true, example = "Category Name")
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: get rid of this by using DTOs
    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.isBlank() && name.length() >= 3 && name.length() <= 255;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductCategory that = (ProductCategory) obj;
        return name.equals(that.name);
    }
}
