package es.com.priceproduct.between.service;


import es.com.priceproduct.between.entity.ProductBEntity;

import java.util.List;

public interface ProductService {
    List<ProductBEntity> findSimilarProducts(Integer productId);
}
