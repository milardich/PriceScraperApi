### About

- API for tracking prices of various items from various web shops

### Usage
- Request: 
`GET` -> `/api/scrape?item-url=https://example-webshop/example-item123`

- Response:
```json
{
  "url": "https://example-webshop/example-item123",
  "name": "Example Item Name",
  "itemImageUrl": "https://example-webshop/example-item-image.jpg",
  "currentPrice": {
    "whole": 149,
    "decimal": 99,
    "currency": "€",
    "scrapingDate": "2024-02-24T02:22:33.744012200"
  },
  "previousPrices": [
    {
      "whole": 149,
      "decimal": 99,
      "currency": "€",
      "scrapingDate": "2024-02-24T02:22:33.7440122"
    },
    {
      "whole": 124,
      "decimal": 99,
      "currency": "€",
      "scrapingDate": "2024-02-24T02:20:20.133543"
    }
  ]
}
```

### Implemented scrapers can be found in this file: 
- `resources/website_scraper_config.json`
```json
{
  "https://www.ikea.com": {
    "priceWholeRegex": "<span\\s+class=\"pip-temp-price__integer\">(\\d+)<\\/span>",
    "priceDecimalRegex": "<span\\s+class=\"pip-temp-price__decimal\"><span\\s+class=\"pip-temp-price__separator\">,<\\/span>(\\d+)<\\/span>",
    "priceCurrencyRegex": "<span\\s+class=\"pip-temp-price__sr-text\">.*?(\\p{Sc}).*?<\\/span>",
    "itemNameRegex": "<span\\s+class=\"pip-header-section__title--big\\s+notranslate\"\\s+translate=\"no\">(.*?)<\\/span>",
    "itemImageRegex": "<img\\s+class=\"pip-image\"\\s+.*?src=\"([^\"]+)\""
  },
  "https://www.hgspot.hr": {
    "priceWholeRegex": "<div itemprop=\"price\" content=\"(\\d+)(?:\\.\\d+)?\"",
    "priceDecimalRegex": "",
    "priceCurrencyRegex": "<div itemprop=\"priceCurrency\" content=\"([A-Z]+)\" class=\"currency\">€<\\/div>\n",
    "itemNameRegex": "<h1 itemprop=\"name\">(.*?)<\\/h1>\n",
    "itemImageRegex": "<img\\s+itemprop=\"image\"\\s+src=\"([^\"]+)\"\\s+width=\"\\d+\"\\s+height=\"\\d+\"\\s+style=\"height:\\s+100%;\">\n"
  },
  ...
}
```

### Docker Setup

- Run these commands
  - `docker-compose up` (from the root directory)
  - `docker exec -it scraper-db-1 bash` 
    - postgres db container name should be `scraper-db-1`. If it isn't, run `docker ps` and fetch the name of postgres db container
  - `psql -U postgres` 
  - `\c price_scraper` (connect to price_scraper database)
  - paste contents from `resources/price-scraper.sql` and press enter
- Scraper API is now running on `localhost:8081`
- Postgres DB is running on `localhost:5433`
- This request should return `200 OK` and app is ready to use
  - `localhost:8081/api/scrape?item-url=https://www.ikea.com/hr/hr/p/markus-uredska-stolica-vissle-tamno-siva-70261150/`  


# TODO

