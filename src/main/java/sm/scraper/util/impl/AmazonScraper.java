package sm.scraper.util.impl;

import sm.scraper.dto.ItemDto;
import sm.scraper.model.Item;
import sm.scraper.util.Scraper;

public class AmazonScraper implements Scraper {
    @Override
    public ItemDto scrape(String url) {
        System.out.println("scrapin AMAAAZON");
        return null;
    }
}
