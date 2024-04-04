package org.vas.product.catalog.core.domain;

import java.util.List;

import org.vas.product.catalog.core.adapters.ProductCategoryRepository;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategory findById(String id) {
        return productCategoryRepository.findById(id);
    }

    public List<ProductCategory> listAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory create(String name) {
        ProductCategory productCategory = new ProductCategory(name);
        if (!productCategory.isValid()) {
            throw new IllegalArgumentException("Invalid product category");
        }
        productCategoryRepository.save(productCategory);
        return productCategory;
    }

    public void update(ProductCategory productCategory) {
        if (!productCategory.isValid()) {
            throw new IllegalArgumentException("Invalid product category");
        }
        productCategoryRepository.update(productCategory);
    }
}
