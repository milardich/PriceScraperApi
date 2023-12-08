package sm.scraper.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sm.scraper.dto.PriceDto;
import sm.scraper.model.Price;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriceMapper {

    @Mapping(target = "whole", source = "whole")
    @Mapping(target = "decimal", source = "decimal")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "scrapingDate", source = "scrapingDate")
    PriceDto toDto(Price price);

    @Mapping(target = "whole", source = "whole")
    @Mapping(target = "decimal", source = "decimal")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "scrapingDate", source = "scrapingDate")
    Price toEntity(PriceDto dto);
}
