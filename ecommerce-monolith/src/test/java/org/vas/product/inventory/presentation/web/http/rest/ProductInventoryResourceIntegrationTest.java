package org.vas.product.inventory.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.vas.product.inventory.core.adapters.ProductInventoryRepository;
import org.vas.product.inventory.core.domain.ProductInventory;
import org.vas.product.inventory.presentation.dtos.CreateProductInventoryDTO;
import org.vas.product.inventory.presentation.dtos.UpdateProductInventoryDTO;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.transaction.Transactional;

@QuarkusTest
@TestHTTPEndpoint(ProductInventoryResource.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductInventoryResourceIntegrationTest {
    @InjectSpy
    private ProductInventoryRepository productRepository;
    private static ProductInventory productToBeUpdated;

    @BeforeAll
    @Transactional
    public static void setup() {
        productToBeUpdated = new ProductInventory("42342342", 10);
        productToBeUpdated.persist();
    }

    @Test
    @Order(1)
    void testGetProducts() {
        List<ProductInventory> productsInventory = ProductInventory.findAll().list();
        var productsCount = productsInventory.size();
        Map<String, ProductInventory> skusInventoryMap = productsInventory.stream()
                .collect(Collectors.toMap(ProductInventory::getSku, Function.identity()));

        given().when().get("").then().statusCode(200).body("size()", is((int) productsCount))
                .body("[0].id", is(1)).body("[0].sku", is("00000001"))
                .body("[0].stockUnits", is(skusInventoryMap.get("00000001").getStockUnits()))
                .body("[1].id", is(51)).body("[1].sku", is("00000002"))
                .body("[1].stockUnits", is(skusInventoryMap.get("00000002").getStockUnits()))
                .body("[2].id", is(101)).body("[2].sku", is("00000003"))
                .body("[2].stockUnits", is(skusInventoryMap.get("00000003").getStockUnits()))
                .body("[3].id", is(151)).body("[3].sku", is("00000004"))
                .body("[3].stockUnits", is(skusInventoryMap.get("00000004").getStockUnits()))
                .body("[4].id", is(201)).body("[4].sku", is("00000005"))
                .body("[4].stockUnits", is(skusInventoryMap.get("00000005").getStockUnits()))
                .body("[5].id", is(251)).body("[5].sku", is("00000006"))
                .body("[5].stockUnits", is(skusInventoryMap.get("00000006").getStockUnits()))
                .body("[6].id", is(301)).body("[6].sku", is("00000007"))
                .body("[6].stockUnits", is(skusInventoryMap.get("00000007").getStockUnits()))
                .body("[7].id", is(351)).body("[7].sku", is("00000008"))
                .body("[7].stockUnits", is(skusInventoryMap.get("00000008").getStockUnits()))
                .body("[8].id", is(401)).body("[8].sku", is("00000009"))
                .body("[8].stockUnits", is(skusInventoryMap.get("00000009").getStockUnits()))
                .body("[9].id", is(451)).body("[9].sku", is("00000011"))
                .body("[9].stockUnits", is(skusInventoryMap.get("00000011").getStockUnits()));

        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
    void testGetProductById() {
        String responseBody = given().when().get("/351").then().statusCode(200).body("id", is(351))
                .body("sku", is("00000008")).body("stockUnits", is(10)).extract().asString();

        assertEquals("{\"id\":351,\"sku\":\"00000008\",\"stockUnits\":10}", responseBody);

        verify(productRepository, times(1)).findProductById(351l);
    }

    @Test
    void testGetProductByIdShouldReturnNotFound() {
        given().when().get("/453453").then().statusCode(404).body("message",
                is("Product inventory not found"));

        verify(productRepository, times(1)).findProductById(453453l);
    }

    @Test
    void testGetProductByIdAsStringShouldReturnError() {
        given().when().get("/aaaa").then().statusCode(404);

        verify(productRepository, times(0)).findProductById(any());
    }

    @Test
    void testCreateProduct() {
        CreateProductInventoryDTO createProductDTO = new CreateProductInventoryDTO("90000009", 8);
        ProductInventory product = given().header("Content-type", "application/json")
                .body(createProductDTO).when().post("").then().statusCode(201)
                .body("id", is(CoreMatchers.any(Integer.class))).body("sku", is("90000009"))
                .body("stockUnits", is(8)).extract().as(ProductInventory.class);

        var newProduct = productRepository.findProductById(product.id).get();
        assertEquals("90000009", newProduct.getSku());
        assertEquals(8, newProduct.getStockUnits());

        verify(productRepository, times(1)).saveProduct(any(ProductInventory.class));
    }

    @Test
    @TestTransaction
    void testUpdateProduct() {
        UpdateProductInventoryDTO updateProductDTO =
                new UpdateProductInventoryDTO(productToBeUpdated.getId(), 350);
        given().header("Content-type", "application/json").body(updateProductDTO).when()
                .patch("/" + productToBeUpdated.getId()).then().statusCode(202).body(is(""));

        verify(productRepository, times(1)).updateProduct(any(ProductInventory.class));
    }

    @Test
    void testPostWithEmptyBody() {
        given().header("Content-type", "application/json").body("").when().post("").then()
                .statusCode(400).body("message", is("No product data provided"));

        verify(productRepository, times(0)).saveProduct(any(ProductInventory.class));
    }

    @Test
    void testPatchWithEmptyBody() {
        given().header("Content-type", "application/json").body("").when().patch("/456").then()
                .statusCode(400).body("message", is("No product data provided"));

        verify(productRepository, times(0)).updateProduct(any(ProductInventory.class));
    }
}
