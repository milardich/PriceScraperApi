DROP TABLE IF EXISTS price;
DROP TABLE IF EXISTS item;

CREATE TABLE IF NOT EXISTS item (
	item_id INTEGER PRIMARY KEY,
	url TEXT,
	item_name TEXT
);

CREATE TABLE IF NOT EXISTS price (
	price_id INTEGER PRIMARY KEY,
	item_id INTEGER,
	price_whole INTEGER,
	price_decimal INTEGER,
	currency VARCHAR(50),
	scraping_date TIMESTAMP
); 