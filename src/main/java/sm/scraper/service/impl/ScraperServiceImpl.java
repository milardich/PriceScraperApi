package sm.scraper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sm.scraper.dto.ItemDto;
import sm.scraper.mapper.ItemMapper;
import sm.scraper.model.Item;
import sm.scraper.repository.ItemRepository;
import sm.scraper.service.ScraperService;
import sm.scraper.util.Scraper;
import sm.scraper.util.Scrapers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ScraperServiceImpl implements ScraperService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemDto scrape(String itemUrl) {
        Scrapers scrapers = new Scrapers();

        // extract website base url
        String baseUrl = extractBaseUrl(itemUrl);

        // get implemented scraper for provided website baseUrl
        Scraper scraper = scrapers.get(baseUrl);
        if(scraper == null) {
            throw new EntityNotFoundException("Scraper for website <" + baseUrl + "> is not implemented");
        }

        // scrape item with full url provided
        ItemDto itemDto = scraper.scrape(itemUrl);

        // TODO: save scraped data to db
        // itemRepository.save(itemMapper.toEntity(itemDto));

        return itemDto;
    }

    private static String extractBaseUrl(String url) {
        String regex = "^(https:?://[^/]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("Error: URL not valid");
            return null;
        }
    }
}
