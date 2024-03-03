CREATE TABLE IF NOT EXISTS item (
	item_id INTEGER PRIMARY KEY,
	url TEXT,
	item_name TEXT,
	item_image_url TEXT
);

CREATE TABLE IF NOT EXISTS price (
	price_id INTEGER PRIMARY KEY,
	item_id INTEGER,
	price_whole INTEGER,
	price_decimal INTEGER,
	currency VARCHAR(50),
	scraping_date TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS item_sequence START 1;
CREATE SEQUENCE IF NOT EXISTS price_sequence START 1;