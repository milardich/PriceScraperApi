package sm.scraper.service.impl;

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
import sm.scraper.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;
    private final ItemMapper itemMapper;

    @Override
    public ItemDto getItemByUrl(String url) {
        Item item = itemRepository.getItemByUrl(url);
        if(item == null) {
            throw new RuntimeException("Item not in database");
        }
        ItemDto itemDto = itemMapper.toDto(item);
        itemDto.setCurrentPrice(itemDto.getPreviousPrices().get(0));
        List<Price> prices = priceRepository.getPricesByItemId(item.getId());
        List<PriceDto> priceDtos = new ArrayList<>();
        prices.forEach(price -> {
            priceDtos.add(priceMapper.toDto(price));
        });
        itemDto.setPreviousPrices(priceDtos);
        return itemDto;
    }
}
