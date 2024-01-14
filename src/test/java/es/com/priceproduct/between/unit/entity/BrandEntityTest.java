package es.com.priceproduct.between.unit.entity;

import es.com.priceproduct.between.entity.BrandEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class BrandEntityTest {

    private BrandEntity brandEntity;

    @BeforeEach
    public void setUp() {
        brandEntity = BrandEntity.builder()
                .id(1l)
                .brandName("BRAND")
                .build();
    }

    @org.junit.jupiter.api.Test
    public void testBrandEntityBuilderAndGettersAndSetters() {

        BrandEntity otherBrandEntity = BrandEntity.builder().build();

        otherBrandEntity.setBrandName(brandEntity.getBrandName());
        otherBrandEntity.setId(brandEntity.getId());

        Boolean isEqual = brandEntity.equals(otherBrandEntity);

        assertEquals(brandEntity, otherBrandEntity);
        assertEquals(brandEntity.hashCode(), otherBrandEntity.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "BrandEntity(id=1, brandName=BRAND)";
        assertEquals(expected, brandEntity.toString());
    }

    @Test
    public void testNotEquals() {

        BrandEntity otherBrandEntity = BrandEntity.builder()
                .id(2l)
                .brandName("BRAND2")
                .build();

        Boolean isEqual = brandEntity.equals(otherBrandEntity);

        assertNotEquals(brandEntity, otherBrandEntity);
        assertNotEquals(brandEntity.hashCode(), otherBrandEntity.hashCode());
        assertFalse(isEqual);
    }
}
