package es.com.priceproduct.between.unit.dto;

import es.com.priceproduct.between.dto.BrandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.*;

public class BrandDtoTest {

    private BrandDto brandDto;

    @BeforeEach
    public void setUp() {
        brandDto = BrandDto.builder()
                .id(1l)
                .name("BRAND")
                .build();
    }

    @Test
    public void testBrandDtoBuilderAndGettersAndSetters() {

        BrandDto otherBrandDto = BrandDto.builder().build();

        otherBrandDto.setName(brandDto.getName());
        otherBrandDto.setId(brandDto.getId());

        Boolean isEqual = brandDto.equals(otherBrandDto);

        assertEquals(brandDto, otherBrandDto);
        assertEquals(brandDto.hashCode(), otherBrandDto.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "BrandDto(id=1, name=BRAND)";
        assertEquals(expected, brandDto.toString());
    }

    @Test
    public void testNotEquals() {

        BrandDto otherBrandDto = BrandDto.builder()
                .id(2l)
                .name("BRAND2")
                .build();

        Boolean isEqual = brandDto.equals(otherBrandDto);

        assertNotEquals(brandDto, otherBrandDto);
        assertNotEquals(brandDto.hashCode(), otherBrandDto.hashCode());
        assertFalse(isEqual);
    }
}
