package org.vas.product.pricing.core.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.adapters.ProductPriceRepository;
import org.vas.product.pricing.core.ports.ProductPriceService;
import org.vas.product.pricing.presentation.dtos.CreateProductPriceDTO;
import org.vas.product.pricing.presentation.dtos.UpdateProductPriceDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductPriceServiceImpl implements ProductPriceService {

    @Inject
    private ProductPriceRepository productRepository;

    public Optional<ProductPrice> findById(Long id) {
        Log.tracef("Getting product price by id: %s", id);
        return productRepository.findProductById(id);
    }

    public Set<ProductPrice> listAll() {
        Log.trace("Listing all products prices");
        return productRepository.findAllProducts();
    }

    public ProductPrice create(CreateProductPriceDTO productDto) {
        Log.debugf("Creating product price: %s", productDto);
        ProductPrice product = new ProductPrice(productDto.sku(), productDto.price());
        if (!product.isValid()) {
            Log.warnf("Attempt to create invalid product: %s", product);
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(UpdateProductPriceDTO productDto, Long id) {
        Log.debugf("Updating product price: %s, %s", id, productDto);
        ProductPrice existingProduct = getExistingProduct(id);
        ProductPrice product = new ProductPrice(id, existingProduct.getSku(), productDto.price());
        if (!product.isValid()) {
            Log.warnf("Attempt to update product with invalid data: %s", product);
            throw new IllegalArgumentException("Invalid product");
        }
        productRepository.updateProduct(product);
    }

    @Override
    public List<ProductPrice> findBySkus(List<String> skus) {
        Log.tracef("Getting product prices by skus: %s", skus);
        return productRepository.findBySkus(skus);
    }

    private ProductPrice getExistingProduct(Long id) {
        var existingProduct = productRepository.findProductById(id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + id + " not found"));
        return existingProduct;
    }
}
