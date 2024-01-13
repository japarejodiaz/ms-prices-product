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
@Table(name = "CURRENCY")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CURRENCY_ID", nullable = false)
    private Long id;

    @Column(name = "CURRENCY_ISO", nullable = false)
    private String currencyIso;

    @Column(name = "CURRENCY_NAME")
    private String currencyName;

}
