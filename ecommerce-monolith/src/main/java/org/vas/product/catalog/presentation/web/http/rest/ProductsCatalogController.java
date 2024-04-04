package org.vas.product.catalog.presentation.web.http.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/products-catalog")
public class ProductsCatalogController {

    // @Inject
    // GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getProductsCatalog() {
        return "products list";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getProductById(@PathParam("id") Long id) {
        return "product by id: " + id;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String createProduct() {
        return "product created";
    }

    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String updateProduct(@PathParam("id") Long id) {
        return "product updated: " + id;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String deleteProduct(@PathParam("id") Long id) {
        return "product deleted: " + id;
    }
}
