package es.com.priceproduct.between.unit.dto;

import es.com.priceproduct.between.dto.ProductDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDtoTest {


    private ProductDto productDto;

    @Before
    public void setUp() {
        productDto = ProductDto.builder()
                .id(1l)
                .name("PRODUCT")
                .build();
    }

    @Test
    public void testProductDtoBuilderAndGettersAndSetters() {

        ProductDto otherProductDto = ProductDto.builder().build();

        otherProductDto.setName(productDto.getName());
        otherProductDto.setId(productDto.getId());

        Boolean isEqual = productDto.equals(otherProductDto);

        assertEquals(productDto, otherProductDto);
        assertEquals(productDto.hashCode(), otherProductDto.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "ProductDto(id=1, name=PRODUCT)";
        assertEquals(expected, productDto.toString());
    }

    @Test
    public void testNotEquals() {
        ProductDto otherProductDto = ProductDto.builder()
                .id(2l)
                .name("PRODUCT2")
                .build();

        Boolean isEqual = productDto.equals(otherProductDto);

        assertNotEquals(productDto, otherProductDto);
        assertNotEquals(productDto.hashCode(), otherProductDto.hashCode());
        assertFalse(isEqual);
    }
}
