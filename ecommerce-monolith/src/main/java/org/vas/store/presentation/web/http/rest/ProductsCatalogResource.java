package org.vas.store.presentation.web.http.rest;

import java.util.Set;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.vas.store.application.ApplicationService;
import org.vas.store.presentation.dtos.ProductCategoryDTO;
import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/store-products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "5. Products Catalog",
        description = "List of all products organized by category to be listed in the store products page")
public class ProductsCatalogResource {

    @Inject
    private ApplicationService appService;

    @GET
    // Cached because the products catalog is not expected to change frequently and we expect a lot
    // of requests to this endpoint
    @CacheResult(cacheName = "store-products-catalog")
    @Operation(
            summary = "List all products organized by category to be listed in the store products page")
    @APIResponse(responseCode = "200", description = "List of all products organized by category")
    public Set<ProductCategoryDTO> getAll() {
        return appService.listProductsCatalog();
    }

    @GET
    @Path("/category/{id:\\d+}")
    // Cached because the products catalog is not expected to change frequently and we expect a lot
    // of requests to this endpoint
    @CacheResult(cacheName = "store-products-catalog-category")
    @Operation(summary = "List all products by category to be listed in the store products page")
    @APIResponse(responseCode = "200", description = "List of all products by category")
    public Set<ProductCategoryDTO> getByCategoryId(@PathParam("id") String categoryId) {
        return appService.listProductsCatalogByCategory(Long.parseLong(categoryId));
    }
}
