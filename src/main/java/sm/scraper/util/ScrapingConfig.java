package sm.scraper.util;

import com.sun.tools.javac.Main;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ScrapingConfig {

    public static Scraper getScraper(String url) {
        try{
            URL resourceUrl = Main.class.getClassLoader().getResource("website_scraper_config.json");
            if(resourceUrl == null) {
                throw new IOException("Resource not found: website_scraper_config.json");
            }

            FileReader fileReader = new FileReader(resourceUrl.getFile());
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            JSONObject concreteScraper = jsonObject.getJSONObject(url);

            Scraper scraper = new Scraper();

            scraper.setWebsiteUrl(url);
            scraper.setPriceWholeRegex((String) concreteScraper.get("priceWholeRegex"));
            scraper.setPriceDecimalRegex((String) concreteScraper.get("priceDecimalRegex"));
            scraper.setPriceCurrencyRegex((String) concreteScraper.get("priceCurrencyRegex"));
            scraper.setItemNameRegex((String) concreteScraper.get("itemNameRegex"));
            scraper.setItemImageRegex((String) concreteScraper.get("itemImageRegex"));
            return scraper;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
