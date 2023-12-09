package sm.scraper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sm.scraper.dto.ItemDto;
import sm.scraper.service.ItemService;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/item")
    public ItemDto getItemByUrl(@RequestParam("url") String url) {
        return itemService.getItemByUrl(url);
    }
}