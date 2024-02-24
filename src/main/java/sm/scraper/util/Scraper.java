package sm.scraper.util;

import lombok.Getter;
import lombok.Setter;
import sm.scraper.dto.ItemDto;
import sm.scraper.dto.PriceDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class Scraper {
    private String websiteUrl;
    private String priceWholeRegex;
    private String priceDecimalRegex;
    private String priceCurrencyRegex;
    private String itemNameRegex;
    private String itemImageRegex;

    public ItemDto scrape(String url){
        String scrapedHTML = Curl.getHtml(url);
        String baseUrl = Curl.extractBaseUrl(url);
        ItemDto itemDto = new ItemDto();
        PriceDto priceDto = new PriceDto();
        itemDto.setUrl(url);
        itemDto.setName(RegexUtil.getStringValue(scrapedHTML, this.itemNameRegex));

        String itemImageUrl = RegexUtil.getStringValue(scrapedHTML, this.itemImageRegex);
        if(itemImageUrl != null && !itemImageUrl.contains(baseUrl)){
            itemImageUrl = baseUrl + '/' + itemImageUrl;
        }
        itemDto.setItemImageUrl(itemImageUrl);

        priceDto.setWhole(RegexUtil.getIntValue(scrapedHTML, this.priceWholeRegex));
        priceDto.setDecimal(RegexUtil.getIntValue(scrapedHTML, this.priceDecimalRegex));
        priceDto.setCurrency(RegexUtil.getStringValue(scrapedHTML, this.priceCurrencyRegex));
        priceDto.setScrapingDate(LocalDateTime.now().toString());

        itemDto.setCurrentPrice(priceDto);

        return itemDto;

    }
}
