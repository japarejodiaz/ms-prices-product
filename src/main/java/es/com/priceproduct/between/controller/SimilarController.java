package es.com.priceproduct.between.controller;

import es.com.priceproduct.between.entity.ProductBEntity;
import es.com.priceproduct.between.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class SimilarController {


    @Autowired
    private ProductService productService;


    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductBEntity>> getSimilarProducts(@PathVariable("productId") Integer productId) {
        // Aquí llamarías a tu servicio para obtener productos similares basados en el productId
        log.info("pase por aqui");
        List<ProductBEntity> similarProducts = productService.findSimilarProducts(productId);

        if (similarProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(similarProducts, HttpStatus.OK);
    }
}
