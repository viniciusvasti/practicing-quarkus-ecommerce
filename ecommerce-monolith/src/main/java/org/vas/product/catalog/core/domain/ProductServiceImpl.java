package org.vas.product.catalog.core.domain;

import java.util.Optional;
import java.util.Set;

import org.vas.product.catalog.core.adapters.ProductRepository;
import org.vas.product.catalog.core.ports.ProductService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findProductById(id);
    }

    public Set<Product> listAll() {
        return productRepository.findAllProducts();
    }

    public Product create(String name, String description, Long categoryId) {
        // TODO: improve this to not pass "" as category name
        Product product = new Product(name, description, new ProductCategory(categoryId, ""));
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(Product product) {
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        productRepository.updateProduct(product);
    }
}
