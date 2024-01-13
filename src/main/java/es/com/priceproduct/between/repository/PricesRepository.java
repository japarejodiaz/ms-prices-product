package es.com.priceproduct.between.repository;

import es.com.priceproduct.between.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PricesRepository extends JpaRepository<PricesEntity, Long>, JpaSpecificationExecutor<PricesEntity> {

}