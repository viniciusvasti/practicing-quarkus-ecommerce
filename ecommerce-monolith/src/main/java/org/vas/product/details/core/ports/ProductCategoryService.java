package org.vas.product.details.core.ports;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.domain.ProductCategory;
import org.vas.product.details.presentation.dtos.UpdateProductCategoryDTO;

public interface ProductCategoryService {
    Optional<ProductCategory> findById(Long id);
    Set<ProductCategory> listAll();
    ProductCategory create(String name);
    void update(UpdateProductCategoryDTO productCategory, Long id);
}
