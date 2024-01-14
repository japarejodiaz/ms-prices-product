package es.com.priceproduct.between.unit.entity;

import es.com.priceproduct.between.entity.ProductEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ProductEntityTest {

    private ProductEntity productEntity;

    @BeforeEach
    public void setUp() {
        productEntity = ProductEntity.builder()
                .id(1l)
                .productName("PRODUCT")
                .build();
    }

    @Test
    public void testProductEntityBuilderAndGettersAndSetters() {

        ProductEntity otherProductEntity = ProductEntity.builder().build();

        otherProductEntity.setProductName(productEntity.getProductName());
        otherProductEntity.setId(productEntity.getId());

        Boolean isEqual = productEntity.equals(otherProductEntity);

        assertEquals(productEntity, otherProductEntity);
        assertEquals(productEntity.hashCode(), otherProductEntity.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "ProductEntity(id=1, productName=PRODUCT)";
        assertEquals(expected, productEntity.toString());
    }

    @Test
    public void testNotEquals() {

        ProductEntity otherProductEntity = ProductEntity.builder()
                .id(2l)
                .productName("PRODUCT2")
                .build();

        Boolean isEqual = productEntity.equals(otherProductEntity);

        assertNotEquals(productEntity, otherProductEntity);
        assertNotEquals(productEntity.hashCode(), otherProductEntity.hashCode());
        assertFalse(isEqual);
    }
}
