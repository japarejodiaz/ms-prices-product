package es.com.priceproduct.between.unit.service.impl;

import es.com.priceproduct.between.constant.ErrorEnum;
import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.entity.PricesEntity;
import es.com.priceproduct.between.exception.PricesNotFoundException;
import es.com.priceproduct.between.mapper.PricesMapper;
import es.com.priceproduct.between.repository.PricesRepository;
import es.com.priceproduct.between.service.impl.PricesServiceImpl;
import es.com.priceproduct.between.unit.utils.UtilsMocks;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PricesServiceImplTest {

    @InjectMocks
    private PricesServiceImpl pricesService;

    @Mock
    @Autowired
    private PricesMapper pricesMapper;

    @Mock
    @Autowired
    private PricesRepository pricesRepository;

    @Mock
    @Autowired
    private MessageSource messageSource;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Test
    public void whenFindPriceWithBrandAndProductOk() {

        LocalDateTime consultationDate = LocalDateTime.of(2020, 06, 15, 10, 0, 0);
        Long brandId = 1L;
        Long productId = 35455l;
        PricesEntity pricesEntity = UtilsMocks.getPricesEntity(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);
        PricesResponse pricesResponse = UtilsMocks.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);

        when(pricesRepository.findAll(any(Specification.class))).thenReturn(List.of(pricesEntity));
        when(pricesMapper.pricesEntityToPricesResponse(pricesEntity)).thenReturn(pricesResponse);

        PricesResponse test = pricesService.getPriceByProductAndBrandAndDate(consultationDate, brandId, productId);
        assertEquals(test,pricesResponse);

    }

    @Test
    public void whenFindPriceWithBrandAndProductNotFound() {
        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35455L;

        String message = "There are no prices defined for product [{0}] on date [{1}]";

        when(messageSource.getMessage(ErrorEnum.NOT_FOUND_PRICE.getMessage(), null, Locale.getDefault())).thenReturn(message);
        PricesNotFoundException exception = assertThrows(PricesNotFoundException.class, () -> {
            pricesService.getPriceByProductAndBrandAndDate(null, brandId, productId);
        });
        System.out.println(exception.getMessage());
        System.out.println(message);
        assertEquals(MessageFormat.format(message, productId.toString(), consultationDate.format(formatter)), exception.getMessage());
    }

}
