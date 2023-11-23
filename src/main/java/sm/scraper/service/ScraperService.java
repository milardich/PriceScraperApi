package sm.scraper.service;

import sm.scraper.dto.ItemDto;
import sm.scraper.model.Item;

public interface ScraperService {
    ItemDto getItemData(String itemUrl);
}
