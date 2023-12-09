package sm.scraper.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "item")
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

    @Column(name = "item_name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<Price> prices;
}
