package es.com.priceproduct.between.unit.service.impl;

import es.com.priceproduct.between.constant.ErrorEnum;
import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.entity.PricesEntity;
import es.com.priceproduct.between.exception.PricesNotFoundException;
import es.com.priceproduct.between.mapper.PricesMapper;
import es.com.priceproduct.between.repository.PricesRepository;
import es.com.priceproduct.between.service.impl.PricesServiceImpl;
import es.com.priceproduct.between.unit.utils.MockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricesServiceImplTest {

    @InjectMocks
    private PricesServiceImpl pricesService;

    @Mock
    private PricesMapper pricesMapper;

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private MessageSource messageSource;

    @Test
    public void whenFindPriceWithBrandAndProductAndDateOk() {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35455l;
        PricesEntity pricesEntity = MockUtils.getPricesEntity(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);
        PricesResponse pricesResponse = MockUtils.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);

        when(pricesRepository.findOne(any(Specification.class))).thenReturn(Optional.of(pricesEntity));
        when(pricesMapper.pricesEntityToPricesResponse(pricesEntity)).thenReturn(pricesResponse);

        PricesResponse test = pricesService.getPriceByProductAndBrandAndDate(consultationDate, brandId, productId);
        assertEquals(test,pricesResponse);
    }

    @Test
    public void whenFindPriceWithBrandAndProductOk() {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35455l;
        PricesEntity pricesEntity = MockUtils.getPricesEntity(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);
        PricesResponse pricesResponse = MockUtils.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);

        when(pricesRepository.findOne(any(Specification.class))).thenReturn(Optional.of(pricesEntity));
        when(pricesMapper.pricesEntityToPricesResponse(pricesEntity)).thenReturn(pricesResponse);

        PricesResponse test = pricesService.getPriceByProductAndBrandAndDate(null, brandId, productId);
        assertEquals(test,pricesResponse);
    }

    @Test(expected = PricesNotFoundException.class)
    public void whenFindPriceWithBrandAndProductNotFound() {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35455l;

        String message = "There are no prices defined for product [{0}] on date [{1}]";

        when(messageSource.getMessage(ErrorEnum.NOT_FOUND_PRICE.getMessage(), null, Locale.getDefault())).thenReturn(message);

        when(pricesRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        pricesService.getPriceByProductAndBrandAndDate(null, brandId, productId);
    }

}
