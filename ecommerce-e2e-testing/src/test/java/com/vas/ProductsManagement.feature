Feature: Products Management
    Background:
        * url 'http://localhost:8080'
        * header Content-Type = 'application/json'
        * header Accept = 'application/json'

    Scenario: Create product details
        Given path '/products'
        And request { "sku": "99999991", "name": "Product A", "description": "Product A description", "categoryId": 1 }
        When method POST
        Then status 201
        And match response == { "id": "#number", "sku": "99999991", "name": "Product A", "description": "Product A description", "category": { "id": 1, "name": "Books" } }
    Scenario: Create product price
        Given path '/products-prices'
        And request { "sku": "99999991", "price": 100.97 }
        When method POST
        Then status 201
        And match response == { "id": "#number", "sku": "99999991", "price": 100.97 }
        And assert responseTime < 50
    Scenario: Create product inventory
        Given path '/products-inventory'
        And request { "sku": "99999991", "stockUnits": 10 }
        When method POST
        Then status 201
        And match response == { "id": "#number", "sku": "99999991", "stockUnits": 10 }
        And assert responseTime < 50