Feature: Order Request
    Background:
        * url 'http://localhost:8080/orders'
        * header Content-Type = 'application/json'
        * header Accept = 'application/json'

    Scenario: Create order request
        Given request { "customerName": "John Doe", "customerEmail": "john.doe@email.com", "shippingAddress": "123 Main St, Springfield, IL 62701", "paymentAmount": 174.97, "items": [ { "sku": "00000019", "quantity": 2 }, { "sku": "00000039", "quantity": 1 } ] }
        When method POST
        Then status 201
        And match response == {"id":"#number","status":"CONFIRMED","paymentAmount":174.97,"items":[{"id":"#number","sku":"00000019","quantity":2},{"id":"#number","sku":"00000039","quantity":1}]}
        And assert responseTime < 1000
        * def orderId = response.id

        Given path orderId
        When method GET
        Then status 200
        And match response == {"id":"#number","status":"CONFIRMED","paymentAmount":174.97,"items":[{"id":"#number","sku":"00000019","quantity":2},{"id":"#number","sku":"00000039","quantity":1}]}
        And retry until response.status == 'SHIPPED'
        