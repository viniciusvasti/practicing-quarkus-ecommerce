package org.vas.product.catalog.core.domain;

import java.util.Set;

public interface ProductCategoryService {
    ProductCategory findById(String id);
    Set<ProductCategory> listAll();
    ProductCategory create(String name);
    void update(ProductCategory productCategory);
}
