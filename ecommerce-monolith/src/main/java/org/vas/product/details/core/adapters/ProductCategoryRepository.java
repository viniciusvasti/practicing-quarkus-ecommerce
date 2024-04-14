package org.vas.product.details.core.adapters;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.domain.ProductCategory;

public interface ProductCategoryRepository {
    ProductCategory saveProductCategory(ProductCategory productCategory);
    Optional<ProductCategory> findProductCategoryById(Long id);
    Set<ProductCategory> findAllProductCategories();
    void updateProductCategory(ProductCategory productCategory);
}
