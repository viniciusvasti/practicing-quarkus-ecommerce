package org.vas.product.catalog.core.adapters;

import java.util.List;

import org.vas.product.catalog.core.domain.ProductCategory;

public interface ProductCategoryRepository {
    void save(ProductCategory productCategory);
    ProductCategory findById(String id);
    List<ProductCategory> findAll();
    void update(ProductCategory productCategory);
}
