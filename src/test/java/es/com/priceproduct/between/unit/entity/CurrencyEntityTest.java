package es.com.priceproduct.between.unit.entity;

import es.com.priceproduct.between.entity.CurrencyEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CurrencyEntityTest {

    private CurrencyEntity currencyEntity;

    @BeforeEach
    public void setUp() {
        currencyEntity = CurrencyEntity.builder()
                .id(1l)
                .currencyName("EURO")
                .currencyIso("EUR")
                .build();
    }

    @Test
    public void testCurrencyEntityBuilderAndGettersAndSetters() {

        CurrencyEntity otherCurrencyEntity = CurrencyEntity.builder().build();

        otherCurrencyEntity.setCurrencyName(currencyEntity.getCurrencyName());
        otherCurrencyEntity.setId(currencyEntity.getId());
        otherCurrencyEntity.setCurrencyIso(currencyEntity.getCurrencyIso());

        Boolean isEqual = currencyEntity.equals(otherCurrencyEntity);

        assertEquals(currencyEntity, otherCurrencyEntity);
        assertEquals(currencyEntity.hashCode(), otherCurrencyEntity.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "CurrencyEntity(id=1, currencyIso=EUR, currencyName=EURO)";
        assertEquals(expected, currencyEntity.toString());
    }

    @Test
    public void testNotEquals() {

        CurrencyEntity otherCurrencyEntity = CurrencyEntity.builder()
                .id(2l)
                .currencyName("DOLAR ESTADOUNIDENSE")
                .currencyIso("USD")
                .build();

        Boolean isEqual = currencyEntity.equals(otherCurrencyEntity);

        assertNotEquals(currencyEntity, otherCurrencyEntity);
        assertNotEquals(currencyEntity.hashCode(), otherCurrencyEntity.hashCode());
        assertFalse(isEqual);

    }
}
