package es.com.priceproduct.between.service.impl;

import es.com.priceproduct.between.entity.ProductBEntity;
import es.com.priceproduct.between.entity.ProductEntity;
import es.com.priceproduct.between.repository.ProductDomainRepository;
import es.com.priceproduct.between.service.ProductService;
import es.com.priceproduct.between.specification.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDomainRepository productDomainRepository;

    @Override
    public List<ProductBEntity> findSimilarProducts(Integer productId) {
        //*** llamar al servicio de lista de similares
        log.info("pase por 2");
        List<Integer> listprod = Arrays.asList(2, 3, 4);
        // validar que el producto exista
        // llamar y crear la especificacion
        log.info("pase por 3");
        Specification<ProductBEntity> spec = ProductSpecification.validOffersProjection(listprod);
        log.info("pase por 4");
        List<ProductBEntity> productDetailList = productDomainRepository.findAll(spec);

        // log.info(productDetailList.toString());

        for (ProductBEntity   listProd: productDetailList) {
            log.info("listProd.get" + listProd.getId());
            log.info("listProd.getname" + listProd.getName());
            log.info("listProd.otros Offer" + listProd.getOfferEntities().get(0).getId());
            log.info("listProd.otros Offer" + listProd.getOfferEntities().get(0).getValidFrom());


         };


        List<ProductBEntity> distinctObjects = productDetailList.stream()
                .collect(Collectors.toMap(ProductBEntity::getId, obj -> obj, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());

        distinctObjects.forEach(System.out::println);


        /*return similarProducts.stream()
                .map(productMapper::mapEntityToProductResponseDto)
                .collect(Collectors.toList());*/

        return null;

    }



}
