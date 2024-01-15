package es.com.priceproduct.between.specification;

import es.com.priceproduct.between.entity.PricesEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PricesSpecification {

    private PricesSpecification() {}

    private static final String BRAND_ID = "id";
    private static final String PRODUCT_ID = "id";
    private static final String BRAND = "brand";
    private static final String PRODUCT = "product";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PRIORITY = "priority";

    public static Specification<PricesEntity> findPriceByCriteria(LocalDateTime consultationDate,
                                                                   Long brandId, Long productId) {

        List<Predicate> predicates = new ArrayList<>();

        return (root, query, criteriaBuilder) -> {

            predicates.add(criteriaBuilder.equal(root.get(BRAND).get(BRAND_ID),brandId));
            predicates.add(criteriaBuilder.equal(root.get(PRODUCT).get(PRODUCT_ID),productId));

            if(Objects.nonNull(consultationDate)) {
                predicates.add(criteriaBuilder.and(
                        criteriaBuilder.lessThanOrEqualTo(root.get(START_DATE),consultationDate),
                        criteriaBuilder.greaterThanOrEqualTo(root.get(END_DATE),consultationDate)));
            }
            query.orderBy(criteriaBuilder.desc(root.get(PRIORITY)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
