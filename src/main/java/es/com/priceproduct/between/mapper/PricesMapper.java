package es.com.priceproduct.between.mapper;


import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.entity.PricesEntity;

public interface PricesMapper {

    PricesResponse pricesEntityToPricesResponse(PricesEntity pricesEntity);

}
