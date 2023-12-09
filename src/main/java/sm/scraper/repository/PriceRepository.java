package sm.scraper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sm.scraper.model.Price;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.item.id = :itemId ORDER BY p.id DESC")
    List<Price> getPricesByItemId(Long itemId);
}
