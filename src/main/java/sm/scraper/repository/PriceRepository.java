package sm.scraper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sm.scraper.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
