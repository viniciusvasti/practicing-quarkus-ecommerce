-- TODO: change increment by 50 to 1
CREATE sequence product_inventory_SEQ START WITH 1 increment by 50;

CREATE sequence product_price_SEQ START WITH 1 increment by 50;

CREATE sequence product_details_SEQ START WITH 1 increment by 50;

CREATE sequence ProductCategory_SEQ START WITH 1 increment by 50;

CREATE TABLE product_details (
    category_id bigint,
    id bigint NOT NULL,
    description varchar(255),
    name varchar(255),
    sku varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE product_inventory (
    stockUnits integer NOT NULL,
    id bigint NOT NULL,
    sku varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE product_price (
    price numeric(38, 2),
    id bigint NOT NULL,
    sku varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE ProductCategory (
    id bigint NOT NULL,
    name varchar(255),
    PRIMARY KEY (id)
);

ALTER TABLE
    IF EXISTS product_details
ADD
    CONSTRAINT FKnkccxbwt1qp2wrqbvb2tfcch4 FOREIGN KEY (category_id) REFERENCES ProductCategory;

-- TODO: Figure out how to run those inserts only for dev and test profiles
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Books');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Electronics');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Clothing');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Home & Kitchen');
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000001', 'The Pragmatic Programmer', 'Your journey to mastery', 1);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000002', 'Clean Code', 'A Handbook of Agile Software Craftsmanship', 1);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000003', 'MacBook Pro', 'The ultimate pro notebook', 51);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000004', 'iPhone 12', 'Blast past fast', 51);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000005', 'Levi''s 501 Original Fit Jeans', 'The original blue jean since 1873', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000006', 'Nike Air Max 270', 'The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000007', 'Instant Pot Duo 7-in-1 Electric Pressure Cooker', 'The best-selling model', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000008', 'Keurig K-Classic Coffee Maker', 'Brews multiple K-Cup Pod sizes', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000009', 'Samsung Galaxy S21 Ultra', 'The ultimate smartphone', 51);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000010', 'Samsung Galaxy Watch 3', 'The most advanced smartwatch', 51);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000011', 'Samsung Galaxy Buds Pro', 'The ultimate earbuds', 51);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000001', 29.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000002', 37.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000003', 1299.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000004', 799.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000005', 59.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000006', 150.00);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000007', 79.95);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000008', 79.99);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000001', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000002', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000003', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000004', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000005', 20);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000006', 15);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000007', 2);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000008', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000009', 0);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000011', 4);
