package org.vas.product.catalog.core.domain;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findById(String id);
    List<ProductCategory> listAll();
    ProductCategory create(String name);
    void update(ProductCategory productCategory);
}
