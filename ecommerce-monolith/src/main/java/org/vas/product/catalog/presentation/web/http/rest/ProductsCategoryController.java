package org.vas.product.catalog.presentation.web.http.rest;

import java.util.Set;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.vas.product.catalog.core.domain.ProductCategory;
import org.vas.product.catalog.core.ports.ProductCategoryService;
import org.vas.product.catalog.presentation.dtos.CreateProductCategoryDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductCategoryDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products-catalog")
public class ProductsCategoryController {

    @Inject
    private ProductCategoryService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<ProductCategory> getProductsCatalog() {
        return service.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public RestResponse<ProductCategory> getProductById(@PathParam("id") Long id) {
        return service
            .findById(id)
            .map(RestResponse::ok)
            .orElse(RestResponse.notFound());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<ProductCategory> createProduct(CreateProductCategoryDTO productCategory) {
        // TODO: handle invalid product category exception
        var created = service.create(productCategory.name());
        return RestResponse.status(Status.CREATED, created);
    }

    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public RestResponse<ProductCategory> updateProduct(@PathParam("id") Long id, UpdateProductCategoryDTO productCategory) {
        // TODO: handle invalid product category exception
        service.update(new ProductCategory(id, productCategory.name()));
        return RestResponse.accepted();
    }
}
