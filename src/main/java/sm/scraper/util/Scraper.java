package sm.scraper.util;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private JSONObject priceWholeRegex;
    private JSONObject priceDecimalRegex;
    private JSONObject priceCurrencyRegex;
    private JSONObject itemNameRegex;
    private JSONObject itemImageRegex;

    public ItemDto scrape(String url){
        String scrapedHTML = Curl.getHtml(url);
        String baseUrl = Curl.extractBaseUrl(url);
        ItemDto itemDto = new ItemDto();
        PriceDto priceDto = new PriceDto();
        itemDto.setUrl(url);

        itemDto.setName(
                RegexUtil.getStringValue(
                        scrapedHTML,
                        this.itemNameRegex.get("regex").toString(),
                        Integer.parseInt(this.itemNameRegex.get("group").toString())
                )
        );

        String itemImageUrl = RegexUtil.getStringValue(
                scrapedHTML,
                this.itemImageRegex.get("regex").toString(),
                Integer.parseInt(this.itemImageRegex.get("group").toString())
        );

        if(itemImageUrl != null && !itemImageUrl.contains(baseUrl)){
            itemImageUrl = baseUrl + '/' + itemImageUrl;
        }

        itemDto.setItemImageUrl(itemImageUrl);

        priceDto.setWhole(RegexUtil.getIntValue(
                scrapedHTML,
                this.priceWholeRegex.get("regex").toString(),
                Integer.parseInt(this.priceWholeRegex.get("group").toString())
            )
        );

        priceDto.setDecimal(
                RegexUtil.getIntValue(
                        scrapedHTML,
                        this.priceDecimalRegex.get("regex").toString(),
                        Integer.parseInt(this.priceDecimalRegex.get("group").toString())
                )
        );

        priceDto.setCurrency(
                RegexUtil.getStringValue(
                        scrapedHTML,
                        this.priceCurrencyRegex.get("regex").toString(),
                        Integer.parseInt(this.priceCurrencyRegex.get("group").toString())
                )
        );

        priceDto.setScrapingDate(LocalDateTime.now().toString());

        itemDto.setCurrentPrice(priceDto);

        return itemDto;

    }
}
