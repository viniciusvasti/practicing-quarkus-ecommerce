package org.vas.product.catalog.core.ports;

import java.util.Optional;
import java.util.Set;

import org.vas.product.catalog.core.domain.ProductCategory;

public interface ProductCategoryService {
    Optional<ProductCategory> findById(Long id);
    Set<ProductCategory> listAll();
    ProductCategory create(String name);
    void update(ProductCategory productCategory);
}
