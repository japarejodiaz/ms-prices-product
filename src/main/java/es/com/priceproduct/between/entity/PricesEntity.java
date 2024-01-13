package es.com.priceproduct.between.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRICE")
public class PricesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PRICE_ID", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BRAND_ID", nullable = false)
  private BrandEntity brand;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRODUCT_ID", nullable = false)
  private ProductEntity product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CURRENCY_ID", nullable = false)
  private CurrencyEntity currency;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @Column(name = "PRICE_LIST", nullable = false)
  private Integer priceList;

  @Column(name = "PRIORITY", nullable = false)
  private Integer priority;

  @Column(name = "PRICE", nullable = false)
  private Double price;

}
