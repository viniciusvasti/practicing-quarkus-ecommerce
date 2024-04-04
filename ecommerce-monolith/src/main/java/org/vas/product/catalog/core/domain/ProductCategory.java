package org.vas.product.catalog.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ProductCategory extends PanacheEntity {
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: get rid of this by using DTOs
    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.isBlank() && name.length() >= 3 && name.length() <= 255
                && name.matches("^[a-zA-Z0-9 ]+$");
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
