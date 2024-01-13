package es.com.priceproduct.between.mapper.impl;

import es.com.priceproduct.between.dto.BrandDto;
import es.com.priceproduct.between.dto.CurrencyDto;
import es.com.priceproduct.between.dto.ProductDto;
import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.entity.BrandEntity;
import es.com.priceproduct.between.entity.CurrencyEntity;
import es.com.priceproduct.between.entity.PricesEntity;
import es.com.priceproduct.between.entity.ProductEntity;
import es.com.priceproduct.between.mapper.PricesMapper;
import org.springframework.stereotype.Component;

@Component
public class PricesMapperImpl implements PricesMapper {

    @Override
    public PricesResponse pricesEntityToPricesResponse(PricesEntity pricesEntity) {
        return PricesResponse.builder()
                .product(productEntityToProductDto(pricesEntity.getProduct()))
                .brand(brandEntityToBrandDto(pricesEntity.getBrand()))
                .priceList(pricesEntity.getPriceList())
                .startDate(pricesEntity.getStartDate())
                .endDate(pricesEntity.getEndDate())
                .price(pricesEntity.getPrice())
                .currency(currencyEntityToCurrencyDto(pricesEntity.getCurrency()))
                .build();
    }

    private CurrencyDto currencyEntityToCurrencyDto(CurrencyEntity currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .name(currency.getCurrencyName())
                .iso(currency.getCurrencyIso())
                .build();
    }

    private BrandDto brandEntityToBrandDto(BrandEntity brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getBrandName())
                .build();
    }

    private ProductDto productEntityToProductDto(ProductEntity product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getProductName())
                .build();
    }
}
