package org.vas.product.pricing.presentation.web.http.rest;

import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.vas.product.pricing.core.domain.Product;
import org.vas.product.pricing.core.ports.ProductService;
import org.vas.product.pricing.presentation.dtos.CreateProductDTO;
import org.vas.product.pricing.presentation.dtos.UpdateProductDTO;

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

@Path("/products-prices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "3. Products Prices", description = "Management of products prices")
public class ProductsController {

    @Inject
    private ProductService service;

    @GET
    @Operation(summary = "List all products prices")
    @APIResponse(responseCode = "200", description = "List of all products")
    public Set<Product> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id:\\d+}")
    @Operation(summary = "Get product price by id")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Product details"),
            @APIResponse(responseCode = "404", description = "Product not found")
    })
    public RestResponse<Product> getById(@PathParam("id") String id) {
        return service
                .findById(Long.parseLong(id))
                .map(RestResponse::ok)
                .orElse(RestResponse.notFound());
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new price for a product")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Product created"),
            @APIResponse(responseCode = "400", description = "Invalid product")
    })
    public RestResponse<Product> create(CreateProductDTO productDto) {
        // TODO: handle invalid product exception
        Product product = new Product(productDto.sku(), productDto.price());
        var created = service.create(product);
        return RestResponse.status(Status.CREATED, created);
    }

    @PATCH
    @Path("/{id:\\d+}")
    @ResponseStatus(StatusCode.ACCEPTED)
    @Transactional
    @Operation(summary = "Update product price")
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Product updated"),
            @APIResponse(responseCode = "400", description = "Invalid product"),
            @APIResponse(responseCode = "404", description = "Product not found")
    })
    public void update(@PathParam("id") String id, UpdateProductDTO product) {
        // TODO: handle invalid product exception
        service.update(new Product(Long.parseLong(id), "", product.price()));
    }
}
