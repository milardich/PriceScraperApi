package sm.scraper.util.impl;

import sm.scraper.dto.ItemDto;
import sm.scraper.dto.PriceDto;
import sm.scraper.util.Curl;
import sm.scraper.util.Scraper;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IkeaScraper implements Scraper {

    @Override
    public ItemDto scrape(String url) {
        ItemDto scrapedItem = new ItemDto();
        PriceDto priceDto = new PriceDto();

        String scrapedHtml = Curl.getHtml(url);

        Integer priceWhole = getPriceWhole(scrapedHtml);
        Integer priceDecimal = getPriceDecimal(scrapedHtml);
        String currency = getPriceCurrency(scrapedHtml);
        String itemName = getItemName(scrapedHtml);

        priceDto.setWhole(priceWhole);
        priceDto.setDecimal(priceDecimal);
        priceDto.setCurrency(currency);
        priceDto.setScrapingDate(LocalDateTime.now().toString());

        scrapedItem.setName(itemName);
        scrapedItem.setUrl(url);
        scrapedItem.setCurrentPrice(priceDto);

        return scrapedItem;
    }

    private Integer getPriceWhole(String html) {
        String regex = "<span\\s+class=\"pip-temp-price__integer\">(\\d+)<\\/span>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {

            return Integer.parseInt(matcher.group(1));
        } else {
            System.out.println("No match found.");
        }

        return null;
    }

    private Integer getPriceDecimal(String html) {
        String regex = "<span\\s+class=\"pip-temp-price__decimal\"><span\\s+class=\"pip-temp-price__separator\">,<\\/span>(\\d+)<\\/span>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {

            return Integer.parseInt(matcher.group(1));
        } else {
            System.out.println("No match found.");
        }

        return null;
    }

    private String getPriceCurrency(String html) {
        String regex = "<span\\s+class=\"pip-temp-price__sr-text\">.*?(\\p{Sc}).*?<\\/span>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("No match found.");
        }

        return null;
    }

    private String getItemName(String html) {
        String regex = "<span\\s+class=\"pip-header-section__title--big\\s+notranslate\"\\s+translate=\"no\">(.*?)<\\/span>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("No match found.");
        }

        return null;
    }
}
