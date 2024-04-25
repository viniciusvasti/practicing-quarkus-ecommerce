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
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Automotive');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Beauty & Personal Care');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Health & Household');
INSERT INTO productCategory (id, name) VALUES (nextval('productcategory_seq'), 'Toys & Games');

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
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000012', 'Samsung Galaxy Tab S7', 'The ultimate tablet', 51);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000013', 'Samsung Galaxy Book Flex', 'The ultimate laptop', 51);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000014', 'Design Patterns: Elements of Reusable Object-Oriented Software', 'Classic book on software design patterns', 1);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000015', 'Head First Design Patterns', 'A Brain-Friendly Guide', 1);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000016', 'Refactoring: Improving the Design of Existing Code', 'The art of refactoring code', 1);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000017', 'Ray-Ban Aviator Sunglasses', 'Iconic sunglasses for a timeless look', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000018', 'Adidas Originals Superstar Shoes', 'Iconic shoes with classic style', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000019', 'Puma Men''s T7 Track Jacket', 'Retro style meets modern comfort', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000020', 'Under Armour Men''s Tech 2.0 Short Sleeve T-Shirt', 'Stay cool and dry during workouts', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000021', 'Levi''s Women''s Original Trucker Jacket', 'Classic denim jacket for everyday wear', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000022', 'Calvin Klein Men''s Cotton Classics Multipack Boxer Briefs', 'Comfortable and stylish underwear', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000023', 'Champion Men''s Powerblend Fleece Pullover Hoodie', 'Cozy hoodie for casual days', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000024', 'Nike Women''s Revolution 5 Running Shoe', 'Lightweight and comfortable running shoes', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000025', 'Adidas Women''s Essentials Linear Full Zip Hoodie', 'Sporty hoodie for active lifestyles', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000026', 'Puma Men''s Essentials Fleece Hoodie', 'Soft and warm hoodie for everyday wear', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000027', 'Under Armour Men''s Tech Graphic Shorts', 'Versatile shorts for workouts and leisure', 101);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000028', 'Instant Pot Duo Crisp Pressure Cooker', 'Pressure cooker with air fryer capabilities', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000029', 'Cuisinart Airfryer Toaster Oven', 'Versatile kitchen appliance for air frying and toasting', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000030', 'Keurig K-Mini Coffee Maker', 'Compact coffee maker for small spaces', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000031', 'Ninja Foodi 5-in-1 Indoor Grill', 'Grill, air fry, roast, bake, and dehydrate in one appliance', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000032', 'Lodge Cast Iron Skillet', 'Classic cast iron skillet for versatile cooking', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000033', 'Instant Pot Ultra 10-in-1 Electric Pressure Cooker', 'Multi-functional pressure cooker for various cooking tasks', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000034', 'KitchenAid Stand Mixer', 'Iconic stand mixer for baking and cooking', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000035', 'Cuisinart Food Processor', 'Powerful food processor for chopping, slicing, and shredding', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000036', 'Pyrex Glass Bakeware Set', 'Durable glass bakeware for baking and serving', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000037', 'NutriBullet Blender', 'Compact blender for making smoothies and shakes', 151);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000038', 'Dyson V11 Torque Drive Cordless Vacuum Cleaner', 'Powerful cordless vacuum cleaner', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000039', 'iRobot Roomba 675 Robot Vacuum', 'Smart robot vacuum for automated cleaning', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000040', 'BISSELL Cleanview Swivel Pet Upright Bagless Vacuum Cleaner', 'Upright vacuum cleaner for pet owners', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000041', 'BLACK+DECKER 20V MAX Cordless Drill', 'Compact cordless drill for DIY projects', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000042', 'DEWALT 20V MAX Cordless Drill Combo Kit', 'Powerful cordless drill combo kit', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000043', 'CRAFTSMAN V20 Cordless Drill/Driver Kit', 'Versatile cordless drill/driver kit', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000044', 'AmazonBasics Portable Air Compressor', 'Compact and portable air compressor', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000045', 'Chemical Guys Complete Car Care Kit', 'All-in-one car care kit for detailing', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000046', 'Armor All Car Cleaning and Leather Wipes', 'Convenient cleaning wipes for cars', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000047', 'Meguiars Ultimate Waterless Wash & Wax', 'Waterless wash and wax solution for cars', 201);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000048', 'CeraVe Hydrating Facial Cleanser', 'Gentle face wash for normal to dry skin', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000049', 'Thayers Alcohol-Free Rose Petal Witch Hazel Toner', 'Natural toner for soft and refreshed skin', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000050', 'Aztec Secret Indian Healing Clay', 'Deep pore cleansing facial mask', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000051', 'TruSkin Vitamin C Serum', 'Anti-aging serum for brightening and firming', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000052', 'Mario Badescu Facial Spray with Aloe, Herbs and Rosewater', 'Refreshing facial mist for hydration', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000053', 'Neutrogena Hydro Boost Hyaluronic Acid Hydrating Gel Cream', 'Lightweight moisturizer for smooth and supple skin', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000054', 'The Ordinary Niacinamide 10% + Zinc 1%', 'Serum for reducing blemishes and congestion', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000055', 'Bioderma Sensibio H2O Micellar Water', 'Micellar water for gentle makeup removal', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000056', 'Paulas Choice Skin Perfecting 2% BHA Liquid Exfoliant', 'Exfoliating solution for smoother skin texture', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000057', 'Olaplex Hair Perfector No. 3 Repairing Treatment', 'Hair treatment for strengthening and repairing', 251);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000058', 'LEGO Classic Creative Bricks Set', 'Build and play with classic LEGO bricks', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000059', 'Barbie Dreamhouse Dollhouse with Pool', 'Dreamhouse playset for Barbie dolls', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000060', 'Hot Wheels 20 Car Gift Pack', 'Collection of 20 Hot Wheels cars', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000061', 'Nerf N-Strike Elite Disruptor Blaster', 'Quick-draw blaster for Nerf battles', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000062', 'Crayola Inspiration Art Case', 'Art set with crayons, pencils, and markers', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000063', 'Melissa & Doug Wooden Building Blocks Set', 'Classic wooden blocks for creative play', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000064', 'Fisher-Price Laugh & Learn Smart Stages Chair', 'Interactive chair for learning and play', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000065', 'VTech Sit-to-Stand Learning Walker', 'Interactive walker for babies and toddlers', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000066', 'Play-Doh Modeling Compound 10-Pack', 'Colorful Play-Doh set for creative fun', 351);
INSERT INTO product_details (id, sku, name, description, category_id) VALUES (nextval('product_details_seq'), '00000067', 'LEGO DUPLO My First Number Train', 'Educational LEGO DUPLO set for toddlers', 351);

INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000001', 29.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000002', 37.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000003', 1299.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000004', 799.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000005', 59.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000006', 150.00);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000007', 79.95);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000008', 79.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000012', 1349.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000013', 1349.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000014', 39.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000015', 49.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000016', 49.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000018', 19.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000019', 24.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000020', 29.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000021', 34.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000022', 39.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000023', 44.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000024', 49.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000025', 54.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000026', 59.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000027', 64.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000028', 69.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000029', 74.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000030', 79.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000031', 84.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000032', 89.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000033', 94.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000034', 99.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000035', 104.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000036', 109.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000037', 114.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000038', 119.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000039', 124.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000040', 129.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000041', 134.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000042', 139.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000043', 144.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000044', 149.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000045', 154.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000046', 159.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000047', 164.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000048', 169.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000049', 174.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000050', 179.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000051', 184.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000052', 189.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000053', 194.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000054', 199.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000055', 204.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000056', 209.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000057', 214.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000058', 219.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000059', 224.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000060', 229.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000061', 234.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000062', 239.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000063', 244.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000064', 249.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000065', 254.99);
INSERT INTO product_price (id, sku, price) VALUES (nextval('product_price_seq'), '00000066', 259.99);

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
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000012', 2);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000013', 1);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000014', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000015', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000016', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000017', 8);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000018', 12);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000019', 15);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000020', 20);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000021', 25);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000022', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000024', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000025', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000026', 20);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000027', 15);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000028', 2);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000029', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000030', 0);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000031', 4);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000032', 2);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000033', 1);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000034', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000035', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000036', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000037', 8);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000038', 12);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000039', 15);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000041', 25);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000042', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000043', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000044', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000045', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000046', 20);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000047', 15);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000048', 2);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000049', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000050', 0);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000051', 4);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000052', 2);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000053', 1);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000054', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000055', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000056', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000057', 8);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000058', 12);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000059', 15);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000060', 20);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000061', 25);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000063', 10);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000064', 3);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000065', 5);
INSERT INTO product_inventory (id, sku, stockunits) VALUES (nextval('product_inventory_seq'), '00000066', 20);

