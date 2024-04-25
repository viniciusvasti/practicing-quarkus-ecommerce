package com.vas;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.commons.logging.Logger;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @BeforeAll
    public static void setup() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080").addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json").build();
        ResponseSpecification responseSpec = new ResponseSpecBuilder().build();
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    @Test
    @Order(1)
    public void shouldCreate3ProductDetails() {
        String payload1 =
                "{\"sku\":\"99999991\",\"name\":\"Product A\",\"description\":\"Product A Description\",\"categoryId\":1}";
        given().body(payload1).when().post("/products").then().statusCode(201)
                .time(lessThan(100L));
        String payload2 =
                "{\"sku\":\"99999992\",\"name\":\"Product B\",\"description\":\"Product B Description\",\"categoryId\":1}";
        given().body(payload2).when().post("/products").then().statusCode(201)
                .time(lessThan(100L));
        String payload3 =
                "{\"sku\":\"99999993\",\"name\":\"Product C\",\"description\":\"Product C Description\",\"categoryId\":101}";
        long responseTime = given().body(payload3).when().post("/products").then().statusCode(201)
                .time(lessThan(100L)).extract().time();
        logger.info(() -> "Products response time: " + responseTime);
    }

    @Test
    @Order(2)
    public void shouldCreate3ProductPrices() {
        String payload1 = "{\"sku\":\"99999991\",\"price\":100.97}";
        given().body(payload1).when().post("/products-prices").then().statusCode(201)
                .time(lessThan(1000L));
        String payload2 = "{\"sku\":\"99999992\",\"price\":200.97}";
        given().body(payload2).when().post("/products-prices").then().statusCode(201)
                .time(lessThan(1000L));
        String payload3 = "{\"sku\":\"99999993\",\"price\":300.97}";
        long responseTime = given().body(payload3).when().post("/products-prices").then()
                .statusCode(201).time(lessThan(1000L)).extract().time();
        logger.info(() -> "Prices response time: " + responseTime);
    }

    @Test
    @Order(3)
    public void shouldCreate3ProductInventory() {
        String payload1 = "{\"sku\":\"99999991\",\"stockUnits\":10}";
        given().body(payload1).when().post("/products-inventory").then().statusCode(201)
                .time(lessThan(1000L));
        String payload2 = "{\"sku\":\"99999992\",\"stockUnits\":20}";
        given().body(payload2).when().post("/products-inventory").then().statusCode(201)
                .time(lessThan(1000L));
        String payload3 = "{\"sku\":\"99999993\",\"stockUnits\":30}";
        long responseTime = given().body(payload3).when().post("/products-inventory").then()
                .statusCode(201).time(lessThan(1000L)).extract().time();
        logger.info(() -> "Inventory response time: " + responseTime);
    }

    @Test
    @Order(4)
    public void shouldCreateAnOrder() {
        String payload = """
                    {
                        "customerName": "John Doe",
                        "customerEmail": "john.doe@email.com",
                        "shippingAddress": "1234 Main St",
                        "paymentAmount": 502.91,
                        "items": [
                            {
                                "sku": "99999991",
                                "quantity": 1
                            },
                            {
                                "sku": "99999992",
                                "quantity": 2
                            }
                        ]
                    }
                """;
        long responseTime = given().body(payload).when().post("/orders").then().statusCode(201)
                .and().body("id", notNullValue()).time(lessThan(3000L)).extract().time();
        logger.info(() -> "Ordering response time: " + responseTime);
    }
}
