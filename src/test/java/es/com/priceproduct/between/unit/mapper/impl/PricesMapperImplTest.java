package es.com.priceproduct.between.unit.mapper.impl;

import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.entity.PricesEntity;
import es.com.priceproduct.between.mapper.impl.PricesMapperImpl;
import es.com.priceproduct.between.unit.utils.UtilsMocks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class PricesMapperImplTest {

    @InjectMocks
    private PricesMapperImpl mapper;

    @Test
    public void whenMapperEntityToDto() {

        LocalDateTime consultationDate = LocalDateTime.now();
        PricesEntity pricesEntity = UtilsMocks.getPricesEntity(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);
        PricesResponse expectedResponse = UtilsMocks.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);

        PricesResponse dto = mapper.pricesEntityToPricesResponse(pricesEntity);
        assertEquals(dto,expectedResponse);

    }
}
