package sm.scraper.util;

import sm.scraper.dto.ItemDto;

public interface Scraper {
    ItemDto scrape(String url);
}
