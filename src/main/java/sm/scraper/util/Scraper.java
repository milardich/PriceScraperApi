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
        itemDto.setName(getStringValue(scrapedHTML, this.itemNameRegex));

        String itemImageUrl = getStringValue(scrapedHTML, this.itemImageRegex);
        if(itemImageUrl != null && !itemImageUrl.contains(baseUrl)){
            itemImageUrl = baseUrl + '/' + itemImageUrl;
        }
        itemDto.setItemImageUrl(itemImageUrl);

        priceDto.setWhole(getIntValue(scrapedHTML, this.priceWholeRegex));
        priceDto.setDecimal(getIntValue(scrapedHTML, this.priceDecimalRegex));
        priceDto.setCurrency(getStringValue(scrapedHTML, this.priceCurrencyRegex));
        priceDto.setScrapingDate(LocalDateTime.now().toString());

        itemDto.setCurrentPrice(priceDto);

        return itemDto;

    }

    private String getStringValue(String html, String regex) {
        if(Objects.equals(regex, "")) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("No match found for regex: " + regex);
        }
        return null;
    }

    private Integer getIntValue(String html, String regex) {
        if(Objects.equals(regex, "")) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            System.out.println("No match found for regex: " + regex);
        }
        return null;
    }
}
