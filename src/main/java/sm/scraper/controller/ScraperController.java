package sm.scraper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sm.scraper.dto.ItemDto;
import sm.scraper.model.Item;
import sm.scraper.service.ScraperService;

@RestController
@RequiredArgsConstructor
public class ScraperController {

    private final ScraperService scraperService;

    @GetMapping("/api/scrape")
    ItemDto getItemData(@RequestParam("item-url") String itemUrl) {
        return scraperService.getItemData(itemUrl);
    }
}
