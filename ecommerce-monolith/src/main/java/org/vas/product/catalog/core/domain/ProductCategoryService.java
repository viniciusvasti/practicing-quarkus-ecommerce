package org.vas.product.catalog.core.domain;

import java.util.Optional;
import java.util.Set;

public interface ProductCategoryService {
    Optional<ProductCategory> findById(Long id);
    Set<ProductCategory> listAll();
    ProductCategory create(String name);
    void update(ProductCategory productCategory);
}
