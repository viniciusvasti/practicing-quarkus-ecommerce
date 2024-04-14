package org.vas.product.details.core.domain;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductDetailsRepository;
import org.vas.product.details.core.ports.ProductDetailsService;
import org.vas.product.details.presentation.dtos.CreateProductDetailsDTO;
import org.vas.product.details.presentation.dtos.UpdateProductDetailsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Inject
    private ProductDetailsRepository productRepository;

    public Optional<ProductDetails> findById(Long id) {
        return productRepository.findProductById(id);
    }

    public Set<ProductDetails> listAll() {
        return productRepository.findAllProducts();
    }

    public ProductDetails create(CreateProductDetailsDTO productDto) {
        ProductDetails product = new ProductDetails(productDto.sku(), productDto.name(),
                productDto.description(), new ProductCategory(productDto.categoryId(), ""));
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(UpdateProductDetailsDTO productDto, Long id) {
        ProductDetails product = new ProductDetails(id, "", productDto.name(),
                productDto.description(), new ProductCategory(productDto.categoryId(), ""));
        var existingProduct = productRepository.findProductById(product.id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + product.id + " not found"));
        product.setSku(existingProduct.getSku());
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        productRepository.updateProduct(product);
    }
}
