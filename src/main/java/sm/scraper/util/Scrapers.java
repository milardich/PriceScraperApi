package sm.scraper.util;

import sm.scraper.util.impl.AmazonScraper;
import sm.scraper.util.impl.IkeaScraper;

import java.util.HashMap;

public class Scrapers {
    private HashMap<String, Scraper> scrapers;
    public Scrapers() {
        scrapers = new HashMap<>();

        // add implemented scrapers here
        scrapers.put("https://www.ikea.com", new IkeaScraper());
        scrapers.put("https://www.amazon.com", new AmazonScraper());
    }

    public Scraper get(String websiteUrl) {
        return this.scrapers.get(websiteUrl);
    }
}
