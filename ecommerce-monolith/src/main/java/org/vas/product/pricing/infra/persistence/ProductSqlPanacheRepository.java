package org.vas.product.pricing.infra.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.adapters.ProductRepository;
import org.vas.product.pricing.core.domain.ProductPrice;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductSqlPanacheRepository implements ProductRepository {

    @Override
    public ProductPrice saveProduct(ProductPrice product) {
        product.persist();
        return product;
    }

    @Override
    public Optional<ProductPrice> findProductById(Long id) {
        return ProductPrice.findByIdOptional(id);
    }

    @Override
    public Set<ProductPrice> findAllProducts() {
        List<ProductPrice> products = ProductPrice.listAll(Sort.by("sku").ascending());
        // LinkedHashSet to keep the order of list elements
        return new LinkedHashSet<ProductPrice>(products);
    }

    @Override
    public void updateProduct(ProductPrice product) {
        Optional<ProductPrice> existingProduct = ProductPrice.findByIdOptional(product.id);
        existingProduct.ifPresent(pc -> {
            pc.setPrice(product.getPrice());
            pc.persist();
        });
    }

}
