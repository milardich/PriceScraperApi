package sm.scraper.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sm.scraper.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT item FROM Item item WHERE item.url = :url")
    Item getItemByUrl(String url);

    @Query("SELECT COUNT(item) > 0 FROM Item item WHERE item.url = :url")
    Boolean itemExistsByUrl(String url);
}
