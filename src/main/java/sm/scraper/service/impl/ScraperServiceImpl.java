package sm.scraper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sm.scraper.dto.ItemDto;
import sm.scraper.dto.PriceDto;
import sm.scraper.mapper.ItemMapper;
import sm.scraper.mapper.PriceMapper;
import sm.scraper.model.Item;
import sm.scraper.model.Price;
import sm.scraper.repository.ItemRepository;
import sm.scraper.repository.PriceRepository;
import sm.scraper.service.ScraperService;
import sm.scraper.util.Curl;
import sm.scraper.util.Scraper;
import sm.scraper.util.ScrapingConfig;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScraperServiceImpl implements ScraperService {

    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;
    private final ItemMapper itemMapper;
    private final PriceMapper priceMapper;

    @Override
    public ItemDto scrape(String itemUrl) {

        // extract website base url
        String baseUrl = Curl.extractBaseUrl(itemUrl);

        // get scraper config from website_scraper_config.json (resources dir)
        Scraper scraper = ScrapingConfig.getScraper(baseUrl);
        if(scraper == null) {
            throw new EntityNotFoundException("Scraper for website " + baseUrl + " not implemented.");
        }

        // scrape item with full url provided
        ItemDto itemDto = scraper.scrape(itemUrl);

        Item item = itemMapper.toEntity(itemDto);
        Price price = priceMapper.toEntity(itemDto.getCurrentPrice());

        // save item to db / get item from db if exists
        Item savedItem;
        if(!itemRepository.itemExistsByUrl(itemUrl)) {
            savedItem = itemRepository.save(item);
        }
        else {
            savedItem = itemRepository.getItemByUrl(itemUrl);
        }

        price.setItem(savedItem);

        priceRepository.save(price);

        // fetch previous prices and return
        List<Price> previousPrices = priceRepository.getPricesByItemId(savedItem.getId());
        List<PriceDto> previousPricesDto = new ArrayList<>();
        previousPrices.forEach(p -> {
            PriceDto priceDto = priceMapper.toDto(p);
            previousPricesDto.add(priceDto);
        });
        itemDto.setPreviousPrices(previousPricesDto);
        return itemDto;
    }
}
