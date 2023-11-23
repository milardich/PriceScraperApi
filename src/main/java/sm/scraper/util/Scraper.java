package sm.scraper.util;

import sm.scraper.dto.ItemDto;
import sm.scraper.model.Item;

public interface Scraper {
    ItemDto scrape(String url);
}
