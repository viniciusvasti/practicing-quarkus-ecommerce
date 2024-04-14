package org.vas.product.details.infra.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductCategoryRepository;
import org.vas.product.details.core.domain.ProductCategory;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductCategorySqlPanacheRepository implements ProductCategoryRepository {

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        productCategory.persist();
        return productCategory;
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(Long id) {
        return ProductCategory.findByIdOptional(id);
    }

    @Override
    public Set<ProductCategory> findAllProductCategories() {
        List<ProductCategory> productCategories = ProductCategory.listAll(Sort.by("name").ascending());
        // LinkedHashSet to keep the order of list elements
        return new LinkedHashSet<ProductCategory>(productCategories);
    }

    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        Optional<ProductCategory> existingProductCategory = ProductCategory.findByIdOptional(productCategory.id);
        existingProductCategory.ifPresent(pc -> {
            pc.setName(productCategory.getName());
            pc.persistAndFlush();
        });
    }

}
