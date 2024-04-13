CREATE sequence customer_order_SEQ START WITH 1 increment by 1;
CREATE sequence customer_order_item_SEQ START WITH 1 increment by 1;

CREATE TABLE customer_order (
    id bigint NOT NULL,
    status varchar(20) NOT NULL,
    paymentAmount numeric(38, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customer_order_item (
    id bigint NOT NULL,
    customer_order_id bigint NOT NULL,
    sku varchar(255),
    quantity integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE
    IF EXISTS customer_order_item
ADD
    CONSTRAINT fk_order_item_order
FOREIGN KEY (customer_order_id) REFERENCES customer_order(id);