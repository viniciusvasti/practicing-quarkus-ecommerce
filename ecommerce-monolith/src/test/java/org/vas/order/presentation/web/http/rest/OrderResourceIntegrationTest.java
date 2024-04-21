package org.vas.order.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.Order;
import org.vas.order.core.domain.OrderStatus;
import org.vas.order.presentation.dtos.CreateOrderDTO;
import org.vas.order.presentation.dtos.CreateOrderItemDTO;
import org.vas.payment.core.adapters.PaymentRepository;
import org.vas.payment.core.domain.Payment;
import org.vas.payment.core.ports.PaymentService;
import org.vas.product.inventory.core.adapters.ProductInventoryRepository;
import org.vas.product.inventory.core.domain.ProductInventory;
import org.vas.product.inventory.core.ports.ProductInventoryService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

@QuarkusTest
@TestHTTPEndpoint(OrderResource.class)
public class OrderResourceIntegrationTest {
    @InjectSpy
    private OrderRepository orderRepository;
    @InjectSpy
    private ProductInventoryService inventoryService;
    @InjectSpy
    private ProductInventoryRepository productRepository;
    @InjectSpy
    private PaymentService paymentService;
    @InjectSpy
    private PaymentRepository paymentRepository;

    @Test
    void testSubmitCorrectOrder() {
        List<CreateOrderItemDTO> orderItems = List.of(new CreateOrderItemDTO("00000001", 2),
                new CreateOrderItemDTO("00000003", 1), new CreateOrderItemDTO("00000005", 3));
        CreateOrderDTO order = new CreateOrderDTO("John Doe", "john.doe@email.com", "123 Main St",
                BigDecimal.valueOf(1539.94), orderItems);

        Order orderResponse = given().header("Content-type", "application/json").body(order).when()
                .post("").then().statusCode(201).body("any { it.key == 'id' }", is(true)).extract()
                .as(Order.class);

        Optional<Order> opCreatedOrder = Order.findByIdOptional(orderResponse.id);
        assertTrue(opCreatedOrder.isPresent());
        assertTrue(opCreatedOrder.isPresent());
        Order createdOrder = opCreatedOrder.get();
        assertEquals(OrderStatus.SHIPPED, createdOrder.getStatus());
        assertEquals(3, createdOrder.getItems().size());

        verify(orderRepository, times(1)).saveOrder(any(Order.class));
        verify(inventoryService, times(1)).decreaseOrderStockUnits(any(Order.class));
        verify(productRepository, times(3)).updateProduct(any(ProductInventory.class));
        verify(paymentService, times(1)).chargeOrder(any(Order.class));
        verify(paymentRepository, times(1)).savePayment(any(Payment.class));
    }

    @Test
    void testSubmitOrderWithNotEnoughStockUnits() {
        List<CreateOrderItemDTO> orderItems = List.of(new CreateOrderItemDTO("00000001", 2),
                new CreateOrderItemDTO("00000003", 4), new CreateOrderItemDTO("00000005", 3));
        CreateOrderDTO order = new CreateOrderDTO("John Doe", "john.doe@email.com", "123 Main St",
                BigDecimal.valueOf(5439.91), orderItems);

        given().header("Content-type", "application/json").body(order).when().post("").then()
                .statusCode(400)
                // TODO: expected error message
                .body("message", is("Not enough items on inventory for product with sku 00000003"));

        verify(orderRepository, times(0)).saveOrder(any(Order.class));
    }

    @Test
    void testSubmitOrderWithLowerPaymentAmount() {
        List<CreateOrderItemDTO> orderItems = List.of(new CreateOrderItemDTO("00000001", 2),
                new CreateOrderItemDTO("00000003", 1), new CreateOrderItemDTO("00000005", 3));
        CreateOrderDTO order = new CreateOrderDTO("John Doe", "john.doe@email.com", "123 Main St",
                BigDecimal.valueOf(1539.93), orderItems);

        given().header("Content-type", "application/json").body(order).when().post("").then()
                .statusCode(400)
                // TODO: expected error message
                .body("message", is("The payment amount is wrong"));

        verify(orderRepository, times(0)).saveOrder(any(Order.class));
    }

    @Test
    void testSubmitOrderWithHigherPaymentAmount() {
        List<CreateOrderItemDTO> orderItems = List.of(new CreateOrderItemDTO("00000001", 2),
                new CreateOrderItemDTO("00000003", 1), new CreateOrderItemDTO("00000005", 3));
        CreateOrderDTO order = new CreateOrderDTO("John Doe", "john.doe@email.com", "123 Main St",
                BigDecimal.valueOf(1539.95), orderItems);

        given().header("Content-type", "application/json").body(order).when().post("").then()
                .statusCode(400)
                // TODO: expected error message
                .body("message", is("The payment amount is wrong"));

        verify(orderRepository, times(0)).saveOrder(any(Order.class));
    }

}
