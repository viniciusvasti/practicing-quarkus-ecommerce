package org.vas.product.details.core.domain;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductCategoryRepository;
import org.vas.product.details.core.ports.ProductCategoryService;
import org.vas.product.details.presentation.dtos.UpdateProductCategoryDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Inject
    private ProductCategoryRepository productCategoryRepository;

    public Optional<ProductCategory> findById(Long id) {
        return productCategoryRepository.findProductCategoryById(id);
    }

    public Set<ProductCategory> listAll() {
        return productCategoryRepository.findAllProductCategories();
    }

    public ProductCategory create(String name) {
        ProductCategory productCategory = new ProductCategory(name);
        if (!productCategory.isValid()) {
            throw new IllegalArgumentException("Invalid product category");
        }
        productCategoryRepository.saveProductCategory(productCategory);
        return productCategory;
    }

    public void update(UpdateProductCategoryDTO productCategoryDto, Long id) {
        ProductCategory productCategory = new ProductCategory(id, productCategoryDto.name());
        if (!productCategory.isValid()) {
            throw new IllegalArgumentException("Invalid product category");
        }
        productCategoryRepository.updateProductCategory(productCategory);
    }
}
