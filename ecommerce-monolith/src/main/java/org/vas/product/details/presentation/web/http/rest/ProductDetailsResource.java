package org.vas.product.details.presentation.web.http.rest;

import java.util.Set;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.vas.product.details.core.domain.ProductDetails;
import org.vas.product.details.core.ports.ProductDetailsService;
import org.vas.product.details.presentation.dtos.CreateProductDetailsDTO;
import org.vas.product.details.presentation.dtos.UpdateProductDetailsDTO;
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

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "2. Products", description = "Management of products details")
public class ProductDetailsResource {

    @Inject
    private ProductDetailsService service;

    @GET
    @Operation(summary = "List all products details (without inventory and price)")
    @APIResponse(responseCode = "200", description = "List of all products")
    public Set<ProductDetails> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id:\\d+}")
    @Operation(summary = "Get product details by id")
    @APIResponse(responseCode = "200", description = "Product details")
    @APIResponse(responseCode = "404", description = "Product not found")
    public RestResponse<ProductDetails> getById(@PathParam("id") String id) {
        return service.findById(Long.parseLong(id)).map(RestResponse::ok)
                .orElse(RestResponse.notFound());
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new product")
    @APIResponse(responseCode = "201", description = "Product created")
    @APIResponse(responseCode = "400", description = "Invalid product")
    public RestResponse<ProductDetails> create(CreateProductDetailsDTO productDto) {
        // TODO: handle invalid product exception
        var created = service.create(productDto);
        return RestResponse.status(Status.CREATED, created);
    }

    @PATCH
    @Path("/{id:\\d+}")
    @ResponseStatus(StatusCode.ACCEPTED)
    @Transactional
    @Operation(summary = "Update product details")
    @APIResponse(responseCode = "202", description = "Product updated")
    @APIResponse(responseCode = "400", description = "Invalid product")
    @APIResponse(responseCode = "404", description = "Product not found")
    public void update(@PathParam("id") String id, UpdateProductDetailsDTO product) {
        // TODO: handle invalid product exception
        service.update(product, Long.parseLong(id));
    }
}
