package org.vas.product.pricing.core.domain;

import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.adapters.ProductRepository;
import org.vas.product.pricing.core.ports.ProductService;

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

    public Product create(Product product) {
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(Product product) {
        var existingProduct = productRepository.findProductById(product.id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + product.id + " not found"));
        product.setSku(existingProduct.getSku());
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        productRepository.updateProduct(product);
    }
}
