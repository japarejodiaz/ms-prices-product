package es.com.priceproduct.between.unit.utils;

import es.com.priceproduct.between.dto.BrandDto;
import es.com.priceproduct.between.dto.CurrencyDto;
import es.com.priceproduct.between.dto.ProductDto;
import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.entity.BrandEntity;
import es.com.priceproduct.between.entity.CurrencyEntity;
import es.com.priceproduct.between.entity.PricesEntity;
import es.com.priceproduct.between.entity.ProductEntity;

import java.time.LocalDateTime;

public class MockUtils {

  public static BrandDto getBrandDto() {
    return BrandDto.builder()
            .name("Zara")
            .id(1l)
            .build();
  }

  public static ProductDto getProductDto() {
    return ProductDto.builder()
            .name("Product")
            .id(35455l)
            .build();
  }

  public static CurrencyDto getCurrencyDto() {
    return CurrencyDto.builder()
            .name("EURO")
            .iso("EUR")
            .id(2l)
            .build();
  }

  public static PricesResponse getPricesResponse(LocalDateTime startDate, LocalDateTime endDate, Double price, Integer priceList) {
    return PricesResponse.builder()
            .brand(getBrandDto())
            .product(getProductDto())
            .currency(getCurrencyDto())
            .startDate(startDate)
            .endDate(endDate)
            .price(price)
            .priceList(priceList)
            .build();
  }

  public static BrandEntity getBrandEntity() {
    return BrandEntity.builder()
            .brandName("Zara")
            .id(1l)
            .build();
  }

  public static ProductEntity getProductEntity() {
    return ProductEntity.builder()
            .productName("Product")
            .id(35455l)
            .build();
  }

  public static CurrencyEntity getCurrencyEntity() {
    return CurrencyEntity.builder()
            .currencyName("EURO")
            .currencyIso("EUR")
            .id(2l)
            .build();
  }

  public static PricesEntity getPricesEntity(LocalDateTime startDate, LocalDateTime endDate, Double price, Integer priceList) {
    return PricesEntity.builder()
            .brand(getBrandEntity())
            .product(getProductEntity())
            .currency(getCurrencyEntity())
            .startDate(startDate)
            .endDate(endDate)
            .price(price)
            .priceList(priceList)
            .priority(1)
            .id(1l)
            .build();
  }

}
