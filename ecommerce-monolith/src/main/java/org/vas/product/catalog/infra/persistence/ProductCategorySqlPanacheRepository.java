package org.vas.product.catalog.infra.persistence;

import java.util.Set;
import java.util.stream.Collectors;

import org.vas.product.catalog.core.adapters.ProductCategoryRepository;
import org.vas.product.catalog.core.domain.ProductCategory;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductCategorySqlPanacheRepository implements ProductCategoryRepository, PanacheRepository<ProductCategory> {

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        persist(productCategory);
        return findById(productCategory.id);
    }

    @Override
    public ProductCategory findProductCategoryById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Set<ProductCategory> findAllProductCategories() {
        return findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
