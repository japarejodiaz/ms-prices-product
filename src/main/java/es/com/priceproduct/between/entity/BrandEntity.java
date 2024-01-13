package es.com.priceproduct.between.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BRAND")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BRAND_ID", nullable = false)
    private Long id;

    @Column(name = "BRAND_NAME")
    private String brandName;
}
