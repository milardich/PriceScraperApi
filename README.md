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

### Setup

-

# TODO

