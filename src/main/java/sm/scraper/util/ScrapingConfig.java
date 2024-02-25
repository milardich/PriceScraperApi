package sm.scraper.util;

import com.sun.tools.javac.Main;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

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

//            concreteScraper.getJSONArray("priceWholeRegex");


            scraper.setWebsiteUrl(url);
            scraper.setPriceWholeRegex(concreteScraper.getJSONArray("priceWholeRegex"));
            scraper.setPriceDecimalRegex(concreteScraper.getJSONArray("priceDecimalRegex"));
            scraper.setPriceCurrencyRegex(concreteScraper.getJSONArray("priceCurrencyRegex"));
            scraper.setItemNameRegex(concreteScraper.getJSONArray("itemNameRegex"));
            scraper.setItemImageRegex(concreteScraper.getJSONArray("itemImageRegex"));
            return scraper;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
