CREATE sequence payment_seq START WITH 1 increment by 50;

CREATE TABLE payment (
    id bigint NOT NULL,
    amount numeric(38, 2) NOT NULL,
    order_id bigint NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE
    IF EXISTS payment
ADD
    CONSTRAINT fk_payment_order
FOREIGN KEY (order_id) REFERENCES customer_order(id);