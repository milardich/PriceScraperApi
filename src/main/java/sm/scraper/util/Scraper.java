package sm.scraper.util;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
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
    private JSONArray priceWholeRegex;
    private JSONArray priceDecimalRegex;
    private JSONArray priceCurrencyRegex;
    private JSONArray itemNameRegex;
    private JSONArray itemImageRegex;

    public ItemDto scrape(String url){
        String scrapedHTML = Curl.getHtml(url);
        String baseUrl = Curl.extractBaseUrl(url);
        ItemDto itemDto = new ItemDto();
        PriceDto priceDto = new PriceDto();
        itemDto.setUrl(url);
        itemDto.setName(RegexUtil.getStringValue(scrapedHTML, this.itemNameRegex.get(0).toString(), Integer.parseInt(this.itemNameRegex.get(1).toString())));

        String itemImageUrl = RegexUtil.getStringValue(scrapedHTML, this.itemImageRegex.get(0).toString(), Integer.parseInt(this.itemImageRegex.get(1).toString()));
        if(itemImageUrl != null && !itemImageUrl.contains(baseUrl)){
            itemImageUrl = baseUrl + '/' + itemImageUrl;
        }
        itemDto.setItemImageUrl(itemImageUrl);

        priceDto.setWhole(RegexUtil.getIntValue(scrapedHTML, this.priceWholeRegex.get(0).toString(), Integer.parseInt(this.priceWholeRegex.get(1).toString())));
        priceDto.setDecimal(RegexUtil.getIntValue(scrapedHTML, this.priceDecimalRegex.get(0).toString(), Integer.parseInt(this.priceDecimalRegex.get(1).toString())));
        priceDto.setCurrency(RegexUtil.getStringValue(scrapedHTML, this.priceCurrencyRegex.get(0).toString(), Integer.parseInt(this.priceCurrencyRegex.get(1).toString())));
        priceDto.setScrapingDate(LocalDateTime.now().toString());

        itemDto.setCurrentPrice(priceDto);

        return itemDto;

    }
}
