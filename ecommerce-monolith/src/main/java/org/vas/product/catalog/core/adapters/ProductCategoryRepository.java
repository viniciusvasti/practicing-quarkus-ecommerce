package org.vas.product.catalog.core.adapters;

import java.util.Set;

import org.vas.product.catalog.core.domain.ProductCategory;

public interface ProductCategoryRepository {
    ProductCategory saveProductCategory(ProductCategory productCategory);
    ProductCategory findProductCategoryById(String id);
    Set<ProductCategory> findAllProductCategories();
    void updateProductCategory(ProductCategory productCategory);
}
