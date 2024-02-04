package es.com.priceproduct.between.entity;

import jakarta.persistence.*;

        import java.util.List;

        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROD")
public class ProductBEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<OfferEntity> offerEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<SizeEntity> sizeEntities;

}