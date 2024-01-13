package es.com.priceproduct.between.unit.dto;

import es.com.priceproduct.between.dto.CurrencyDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyDtoTest {

    private CurrencyDto currencyDto;

    @Before
    public void setUp() {
        currencyDto = CurrencyDto.builder()
                .id(1l)
                .name("EURO")
                .iso("EUR")
                .build();
    }

    @Test
    public void testCurrencyDtoBuilderAndGettersAndSetters() {

        CurrencyDto otherCurrencyDto = CurrencyDto.builder().build();

        otherCurrencyDto.setName(currencyDto.getName());
        otherCurrencyDto.setId(currencyDto.getId());
        otherCurrencyDto.setIso(currencyDto.getIso());

        Boolean isEqual = currencyDto.equals(otherCurrencyDto);

        assertEquals(currencyDto, otherCurrencyDto);
        assertEquals(currencyDto.hashCode(), otherCurrencyDto.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "CurrencyDto(id=1, name=EURO, iso=EUR)";
        assertEquals(expected, currencyDto.toString());
    }

    @Test
    public void testNotEquals() {

        CurrencyDto otherCurrencyDto = CurrencyDto.builder()
                .id(2l)
                .name("DOLAR ESTADOUNIDENSE")
                .iso("USD")
                .build();

        Boolean isEqual = currencyDto.equals(otherCurrencyDto);

        assertNotEquals(currencyDto, otherCurrencyDto);
        assertNotEquals(currencyDto.hashCode(), otherCurrencyDto.hashCode());
        assertFalse(isEqual);

    }
}
