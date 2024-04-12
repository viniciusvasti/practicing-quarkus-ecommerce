package org.vas.product.catalog.presentation.web.http.rest;

import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
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
@Tag(name = "1. Products Category", description = "Management of products categories")
public class ProductsCategoryController {

    @Inject
    private ProductCategoryService service;

    @GET
    @Operation(summary = "List all products categories")
    @APIResponse(responseCode = "200", description = "List of all products categories")
    public Set<ProductCategory> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id:\\d+}")
    @Operation(summary = "Get product category details by id")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Product category details"),
        @APIResponse(responseCode = "404", description = "Product category not found")
    })
    public RestResponse<ProductCategory> getById(@PathParam("id") String id) {
        return service
            .findById(Long.parseLong(id))
            .map(RestResponse::ok)
            .orElse(RestResponse.notFound());
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new product category")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Product category created"),
        @APIResponse(responseCode = "400", description = "Invalid product category")
    })
    public RestResponse<ProductCategory> create(CreateProductCategoryDTO productCategory) {
        // TODO: handle invalid product category exception
        var created = service.create(productCategory.name());
        return RestResponse.status(Status.CREATED, created);
    }

    @PATCH
    @Path("/{id:\\d+}")
    @ResponseStatus(StatusCode.ACCEPTED)
    @Transactional
    @Operation(summary = "Update product category details")
    @APIResponses({
        @APIResponse(responseCode = "202", description = "Product category updated"),
        @APIResponse(responseCode = "400", description = "Invalid product category")
    })
    public void update(@PathParam("id") String id, UpdateProductCategoryDTO productCategory) {
        // TODO: handle invalid product category exception
        service.update(new ProductCategory(Long.parseLong(id), productCategory.name()));
    }
}
