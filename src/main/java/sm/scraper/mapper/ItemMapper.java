package sm.scraper.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sm.scraper.dto.ItemDto;
import sm.scraper.model.Item;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {

    @Mapping(target = "url", source = "url")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "previousPrices", source = "prices")
    ItemDto toDto(Item item);

    @Mapping(target = "url", source = "url")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "prices", source = "previousPrices")
    Item toEntity(ItemDto dto);
}
