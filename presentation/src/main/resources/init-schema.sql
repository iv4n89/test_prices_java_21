DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
    brand_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list INTEGER NOT NULL,
    priority INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    curr VARCHAR(3) NOT NULL,
    CONSTRAINT prices_pkey PRIMARY KEY(brand_id, product_id, start_date, end_date, priority)
);

CREATE INDEX idx_prices_brand_product_dates ON prices (brand_id, product_id, start_date, end_date);
CREATE INDEX idx_prices_dates_priority ON prices (start_date, end_date, priority DESC);
CREATE INDEX idx_prices_price_list ON prices (price_list);
CREATE INDEX idx_prices_lookup ON prices (brand_id, product_id, priority DESC, start_date, end_date);

INSERT INTO prices (brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (1, 35455, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 0, 35.50, 'EUR');

INSERT INTO prices (brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (1, 35455, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 2, 1, 25.45, 'EUR');

INSERT INTO prices (brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (1, 35455, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 3, 1, 30.50, 'EUR');

INSERT INTO prices (brand_id, product_id, start_date, end_date, price_list, priority, price, curr)
VALUES (1, 35455, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 4, 1, 38.95, 'EUR');
