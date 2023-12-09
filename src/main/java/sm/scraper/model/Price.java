package sm.scraper.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@Getter
@Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_sequence")
    @SequenceGenerator(name="price_sequence", allocationSize = 1)
    @Column(name = "price_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "price_whole")
    private Integer whole;

    @Column(name = "price_decimal")
    private Integer decimal;

    @Column(name = "currency")
    private String currency;

    @Column(name = "scraping_date")
    private LocalDateTime scrapingDate;
}
