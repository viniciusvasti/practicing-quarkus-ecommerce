package org.vas.product.catalog.presentation.web.http.rest;

import java.util.Set;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.vas.product.catalog.core.domain.Product;
import org.vas.product.catalog.core.domain.ProductCategory;
import org.vas.product.catalog.core.ports.ProductService;
import org.vas.product.catalog.presentation.dtos.CreateProductDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductDTO;

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
public class ProductsController {

    @Inject
    private ProductService service;

    @GET
    public Set<Product> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id:\\d+}")
    public RestResponse<Product> getById(@PathParam("id") String id) {
        return service
                .findById(Long.parseLong(id))
                .map(RestResponse::ok)
                .orElse(RestResponse.notFound());
    }

    @POST
    @Transactional
    public RestResponse<Product> create(CreateProductDTO productDto) {
        // TODO: handle invalid product exception
        Product product = new Product(productDto.sku(), productDto.name(), productDto.description(),
                new ProductCategory(productDto.categoryId(), ""));
        var created = service.create(product);
        return RestResponse.status(Status.CREATED, created);
    }

    @PATCH
    @Path("/{id:\\d+}")
    @ResponseStatus(StatusCode.ACCEPTED)
    @Transactional
    public void update(@PathParam("id") String id, UpdateProductDTO product) {
        // TODO: handle invalid product exception
        service.update(new Product(Long.parseLong(id), "", product.name(), product.description(),
                new ProductCategory(product.categoryId(), "")));
    }
}
