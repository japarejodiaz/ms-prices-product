package es.com.priceproduct.between.repository;

import es.com.priceproduct.between.entity.ProductBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDomainRepository extends JpaRepository<ProductBEntity, Integer>, JpaSpecificationExecutor<ProductBEntity> {
}
