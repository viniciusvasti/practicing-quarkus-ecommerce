package org.vas.product.details.core.domain;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductCategoryRepository;
import org.vas.product.details.core.ports.ProductCategoryService;
import org.vas.product.details.presentation.dtos.UpdateProductCategoryDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Inject
    private ProductCategoryRepository productCategoryRepository;

    public Optional<ProductCategory> findById(Long id) {
        Log.tracef("Getting product category details by id: %s", id);
        return productCategoryRepository.findProductCategoryById(id);
    }

    public Set<ProductCategory> listAll() {
        Log.trace("Listing all products categories");
        return productCategoryRepository.findAllProductCategories();
    }

    public ProductCategory create(String name) {
        Log.debugf("Creating new product category: %s", name);
        ProductCategory productCategory = new ProductCategory(name);
        if (!productCategory.isValid()) {
            Log.warnf("Attempt to create invalid product category: %s", productCategory);
            throw new IllegalArgumentException("Invalid product category");
        }
        productCategoryRepository.saveProductCategory(productCategory);
        return productCategory;
    }

    public void update(UpdateProductCategoryDTO productCategoryDto, Long id) {
        Log.debugf("Updating product category: %s, %s", id, productCategoryDto);
        ProductCategory productCategory = new ProductCategory(id, productCategoryDto.name());
        if (!productCategory.isValid()) {
            Log.warnf("Attempt to update product category with invalid data: %s", productCategory);
            throw new IllegalArgumentException("Invalid product category");
        }
        productCategoryRepository.updateProductCategory(productCategory);
    }
}
