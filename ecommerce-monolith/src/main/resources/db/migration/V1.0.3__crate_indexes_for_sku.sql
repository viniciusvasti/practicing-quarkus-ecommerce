CREATE INDEX product_details_sku_idx ON product_details(sku);
CREATE INDEX product_price_sku_idx ON product_price(sku);
CREATE INDEX product_inventory_sku_idx ON product_inventory(sku);

ALTER TABLE product_details ADD CONSTRAINT product_details_sku_unique UNIQUE(sku);
ALTER TABLE product_price ADD CONSTRAINT product_price_sku_unique UNIQUE(sku);
ALTER TABLE product_inventory ADD CONSTRAINT product_inventory_sku_unique UNIQUE(sku);