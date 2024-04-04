package org.vas.product.catalog.core.domain;

import java.util.UUID;

public class ProductCategory {
    private UUID id;
    private String name;

    public ProductCategory(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return id.equals(that.id);
    }
}
