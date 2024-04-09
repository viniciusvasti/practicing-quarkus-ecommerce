package org.vas.product.catalog.presentation.web.http.rest;

import java.util.Set;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.vas.product.catalog.core.domain.ProductCategory;
import org.vas.product.catalog.core.ports.ProductCategoryService;
import org.vas.product.catalog.presentation.dtos.CreateProductCategoryDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductCategoryDTO;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products-category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductsCategoryController {

    @Inject
    private ProductCategoryService service;

    @GET
    public Set<ProductCategory> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id:\\d+}")
    public RestResponse<ProductCategory> getById(@PathParam("id") String id) {
        return service
            .findById(Long.parseLong(id))
            .map(RestResponse::ok)
            .orElse(RestResponse.notFound());
    }

    @POST
    @Transactional
    public RestResponse<ProductCategory> create(CreateProductCategoryDTO productCategory) {
        // TODO: handle invalid product category exception
        var created = service.create(productCategory.name());
        return RestResponse.status(Status.CREATED, created);
    }

    @PATCH
    @Path("/{id:\\d+}")
    @ResponseStatus(StatusCode.ACCEPTED)
    @Transactional
    public void update(@PathParam("id") String id, UpdateProductCategoryDTO productCategory) {
        // TODO: handle invalid product category exception
        service.update(new ProductCategory(Long.parseLong(id), productCategory.name()));
    }
}
