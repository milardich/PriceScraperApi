package sm.scraper.service;

import sm.scraper.dto.ItemDto;

public interface ItemService {
    ItemDto getItemByUrl(String url);
}
