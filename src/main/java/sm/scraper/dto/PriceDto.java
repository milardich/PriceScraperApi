package sm.scraper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDto {
    private Integer whole;
    private Integer decimal;
    private String currency;
}
