package sm.scraper.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ItemDto {
    private String url;
    private String name;
    private String itemImageUrl;
    private PriceDto currentPrice;
    private List<PriceDto> previousPrices;
}
