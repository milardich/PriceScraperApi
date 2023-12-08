package sm.scraper.service;

import sm.scraper.dto.ItemDto;

public interface ScraperService {
    ItemDto scrape(String itemUrl);
}
