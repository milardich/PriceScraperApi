package sm.scraper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import sm.scraper.dto.ItemDto;
import sm.scraper.model.Item;
import sm.scraper.service.ScraperService;
import sm.scraper.util.Scraper;
import sm.scraper.util.Scrapers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScraperServiceImpl implements ScraperService {

    @Override
    public ItemDto getItemData(String itemUrl) {
        Scrapers scrapers = new Scrapers();

        // receive url of an item from request
        //String itemUrl = "https://www.ikea.com/hr/hr/p/markus-uredska-stolica-vissle-tamno-siva-70261150/";

        // extract website base url
        String baseUrl = extractBaseUrl(itemUrl);

        // get implemented scraper for provided website baseUrl
        Scraper scraper = scrapers.get(baseUrl);
        if(scraper == null) {
            throw new EntityNotFoundException("Scraper for website <" + baseUrl + "> is not implemented");
        }

        // scrape item with full url provided
        return scraper.scrape(itemUrl);
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
