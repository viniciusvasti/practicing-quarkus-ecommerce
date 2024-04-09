package org.vas.store.presentation.web.http.rest;

import java.util.Set;

import org.vas.store.application.ApplicationService;
import org.vas.store.presentation.dtos.ProductCategoryDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/store-products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductsCatalogController {

    @Inject
    private ApplicationService appService;

    // TODO: cache this response since it's a heavy operation
    @GET
    public Set<ProductCategoryDTO> getAll() {
        return appService.listProductsCatalog();
    }
}
