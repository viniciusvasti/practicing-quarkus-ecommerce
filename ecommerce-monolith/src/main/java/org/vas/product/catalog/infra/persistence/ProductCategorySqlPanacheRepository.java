package org.vas.product.catalog.infra.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.vas.product.catalog.core.adapters.ProductCategoryRepository;
import org.vas.product.catalog.core.domain.ProductCategory;

import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductCategorySqlPanacheRepository implements ProductCategoryRepository {

    @Override
    @Transactional
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        productCategory.persistAndFlush();
        return productCategory;
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(Long id) {
        return ProductCategory.findByIdOptional(id);
    }

    @Override
    public Set<ProductCategory> findAllProductCategories() {
        List<ProductCategory> productCategories = ProductCategory.listAll(Sort.by("name", Direction.Ascending));
        return productCategories.stream().collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void updateProductCategory(ProductCategory productCategory) {
        Optional<ProductCategory> existingProductCategory = ProductCategory.findByIdOptional(productCategory.id);
        existingProductCategory.ifPresent(pc -> {
            pc.setName(productCategory.getName());
            pc.persistAndFlush();
        });
    }

}
