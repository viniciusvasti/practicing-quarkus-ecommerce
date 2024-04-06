package org.vas.product.catalog.testsconfig;

import org.vas.product.catalog.core.domain.ProductCategory;
import org.vas.product.catalog.infra.persistence.ProductCategorySqlPanacheRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@Priority(1)
@Alternative
@ApplicationScoped
public class TestProductCategorySqlPanacheRepository extends ProductCategorySqlPanacheRepository {
    @PostConstruct
    void init() {
        // saveProductCategory(new ProductCategory("category 1"));
        // saveProductCategory(new ProductCategory("category 2"));
        // saveProductCategory(new ProductCategory("category 3"));
    }
}
