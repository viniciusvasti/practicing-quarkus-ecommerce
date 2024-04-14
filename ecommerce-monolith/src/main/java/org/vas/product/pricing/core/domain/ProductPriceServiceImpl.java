package org.vas.product.pricing.core.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.adapters.ProductPriceRepository;
import org.vas.product.pricing.core.ports.ProductPriceService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductPriceServiceImpl implements ProductPriceService {

    @Inject
    private ProductPriceRepository productRepository;

    public Optional<ProductPrice> findById(Long id) {
        return productRepository.findProductById(id);
    }

    public Set<ProductPrice> listAll() {
        return productRepository.findAllProducts();
    }

    public ProductPrice create(ProductPrice product) {
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(ProductPrice product) {
        var existingProduct = productRepository.findProductById(product.id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + product.id + " not found"));
        product.setSku(existingProduct.getSku());
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        productRepository.updateProduct(product);
    }

    @Override
    public List<ProductPrice> findBySkus(List<String> skus) {
        return productRepository.findBySkus(skus);
    }
}
