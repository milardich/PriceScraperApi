package sm.scraper.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemDto {
    private String url;
    private String name;
    private PriceDto price;
}
