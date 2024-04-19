package org.vas.product.details.core.domain;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductDetailsRepository;
import org.vas.product.details.core.ports.ProductDetailsService;
import org.vas.product.details.presentation.dtos.CreateProductDetailsDTO;
import org.vas.product.details.presentation.dtos.UpdateProductDetailsDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Inject
    private ProductDetailsRepository productRepository;

    public Optional<ProductDetails> findById(Long id) {
        Log.tracef("Getting product details by id: %s", id);
        return productRepository.findProductById(id);
    }

    public Set<ProductDetails> listAll() {
        Log.trace("Listing all products details");
        return productRepository.findAllProducts();
    }

    public ProductDetails create(CreateProductDetailsDTO productDto) {
        Log.debugf("Creating new product: %s", productDto);
        ProductDetails product = new ProductDetails(productDto.sku(), productDto.name(),
                productDto.description(), new ProductCategory(productDto.categoryId(), ""));
        if (!product.isValid()) {
            Log.warnf("Attempt to create invalid product: %s", product);
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(UpdateProductDetailsDTO productDto, Long id) {
        Log.debugf("Updating product details: %s, %s", id, productDto);
        ProductDetails product = new ProductDetails(id, "", productDto.name(),
                productDto.description(), new ProductCategory(productDto.categoryId(), ""));
        var existingProduct = productRepository.findProductById(product.id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + product.id + " not found"));
        product.setSku(existingProduct.getSku());
        if (!product.isValid()) {
            Log.warnf("Attempt to update product with invalid data: %s", product);
            throw new IllegalArgumentException("Invalid product ");
        }
        productRepository.updateProduct(product);
    }
}
