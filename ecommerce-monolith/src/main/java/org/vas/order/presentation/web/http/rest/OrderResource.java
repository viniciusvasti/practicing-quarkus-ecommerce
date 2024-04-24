package org.vas.order.presentation.web.http.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.vas.order.core.domain.Order;
import org.vas.order.core.domain.exceptions.NotEnoughStockUnitsException;
import org.vas.order.core.domain.exceptions.WrongPaymentAmountException;
import org.vas.order.core.ports.OrderService;
import org.vas.order.presentation.dtos.CreateOrderDTO;
import org.vas.shared.presentation.dtos.ErrorResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "6. Order", description = "Order operations")
public class OrderResource {

    @ServerExceptionMapper
    public RestResponse<ErrorResponseDTO> mapException(NotEnoughStockUnitsException nese) {
        return RestResponse.status(Status.BAD_REQUEST, new ErrorResponseDTO(nese.getMessage()));
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponseDTO> mapException(WrongPaymentAmountException wpae) {
        return RestResponse.status(Status.BAD_REQUEST, new ErrorResponseDTO(wpae.getMessage()));
    }

    @Inject
    private OrderService service;

    @GET
    @Operation(summary = "List all orders")
    @APIResponse(responseCode = "200", description = "List of all orders")
    public RestResponse<Iterable<Order>> getAll() {
        return RestResponse.ok(service.listAll());
    }

    @GET
    @Path("/{id:\\d+}")
    @Operation(summary = "Get order by id")
    @APIResponses(value = {@APIResponse(responseCode = "200", description = "Order found"),
            @APIResponse(responseCode = "404", description = "Order not found")})
    public RestResponse<Order> get(String id) {
        return service.findById(Long.parseLong(id)).map(RestResponse::ok)
                .orElse(RestResponse.notFound());
    }

    @POST
    @Operation(summary = "Create a new order")
    @APIResponses(value = {@APIResponse(responseCode = "201", description = "Order created"),
            @APIResponse(responseCode = "400", description = "Invalid order data")})
    public RestResponse<Order> post(CreateOrderDTO orderDto) {
        var created = service.requestOrder(orderDto);
        return RestResponse.status(Status.CREATED, created);
    }
}
