package sm.scraper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    @SequenceGenerator(name="item_sequence", allocationSize = 1)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "price_whole")
    private Integer priceWhole;

    @Column(name = "price_decimal")
    private Integer priceDecimal;

    @Column(name = "currency")
    private String currency;
}
