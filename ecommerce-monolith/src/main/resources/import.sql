-- Inserting product categories
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Books');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Electronics');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Clothing');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Home & Kitchen');

-- Inserting products
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000001', 'The Pragmatic Programmer', 'Your journey to mastery', 1);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000002', 'Clean Code', 'A Handbook of Agile Software Craftsmanship', 1);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000003', 'MacBook Pro', 'The ultimate pro notebook', 51);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000004', 'iPhone 12', 'Blast past fast', 51);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000005', 'Levi''s 501 Original Fit Jeans', 'The original blue jean since 1873', 101);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000006', 'Nike Air Max 270', 'The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93', 101);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000007', 'Instant Pot Duo 7-in-1 Electric Pressure Cooker', 'The best-selling model', 151);
INSERT INTO product (id, sku, name, description, category_id) VALUES (nextval('product_seq'), '00000008', 'Keurig K-Classic Coffee Maker', 'Brews multiple K-Cup Pod sizes', 151);