package org.vas.product.details.infra.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductDetailsRepository;
import org.vas.product.details.core.domain.ProductDetails;
import org.vas.product.details.core.domain.ProductCategory;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductDetailsSqlPanacheRepository implements ProductDetailsRepository {

    @Override
    public ProductDetails saveProduct(ProductDetails product) {
        // TODO: handle category not found
        Optional<ProductCategory> categoryOp = ProductCategory.findByIdOptional(product.getCategory().getId());
        if (categoryOp.isEmpty()) {
            throw new IllegalArgumentException("Category with id " + product.getCategory().getId() + " not found");
        }
        product.setCategory(categoryOp.get());
        product.persist();
        return product;
    }

    @Override
    public Optional<ProductDetails> findProductById(Long id) {
        return ProductDetails.findByIdOptional(id);
    }

    @Override
    public Set<ProductDetails> findAllProducts() {
        List<ProductDetails> products = ProductDetails.listAll(Sort.by("name").ascending());
        // LinkedHashSet to keep the order of list elements
        return new LinkedHashSet<ProductDetails>(products);
    }

    @Override
    public void updateProduct(ProductDetails product) {
        Optional<ProductDetails> existingProduct = ProductDetails.findByIdOptional(product.id);
        existingProduct.ifPresent(pc -> {
            Optional<ProductCategory> categoryOp = ProductCategory.findByIdOptional(product.getCategory().getId());
            if (categoryOp.isEmpty()) {
                throw new IllegalArgumentException("Category with id " + product.getCategory().getId() + " not found");
            }

            product.setCategory(categoryOp.get());
            pc.setName(product.getName());
            pc.setDescription(product.getDescription());
            pc.persistAndFlush();
        });
    }

}
