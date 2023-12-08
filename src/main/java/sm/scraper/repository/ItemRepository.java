package sm.scraper.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sm.scraper.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
