package es.com.priceproduct.between.service;

import es.com.priceproduct.between.dto.response.PricesResponse;

import java.time.LocalDateTime;

public interface PricesService {

    PricesResponse getPriceByProductAndBrandAndDate(LocalDateTime consultationDate, Long brandId, Long productId);

}
