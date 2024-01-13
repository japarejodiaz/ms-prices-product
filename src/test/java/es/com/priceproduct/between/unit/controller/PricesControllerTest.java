package es.com.priceproduct.between.unit.controller;

import es.com.priceproduct.between.constant.ErrorEnum;
import es.com.priceproduct.between.controller.CustomExceptionHandler;
import es.com.priceproduct.between.controller.PricesController;
import es.com.priceproduct.between.exception.PricesNotFoundException;
import es.com.priceproduct.between.service.PricesService;
import es.com.priceproduct.between.unit.utils.MockUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@EnableWebMvc
public class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PricesService pricesService;

    @InjectMocks
    private PricesController pricesController;

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @Mock
    private MessageSource messageSource;

    private static final String baseUrl = "/v1/api/prices";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(pricesController)
                .setControllerAdvice(customExceptionHandler)
                .addPlaceholderValue("openapi.dev-url", baseUrl).build();
    }

    @Test
    public void testGetPriceByProductAndBrandAndDateOK() throws Exception {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35455l;

        when(pricesService.getPriceByProductAndBrandAndDate(consultationDate, brandId, productId))
                .thenReturn(MockUtils.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1));

        mockMvc.perform(get(baseUrl)
                        .param("consultationDate", consultationDate.toString())
                        .param("brandId", brandId.toString())
                        .param("productId", productId.toString())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product.id", Matchers.is(35455)))
                .andExpect(jsonPath("$.product.name", Matchers.is("Product")))
                .andExpect(jsonPath("$.brand.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand.name", Matchers.is("Zara")))
                .andExpect(jsonPath("$.currency.iso", Matchers.is("EUR")))
                .andExpect(jsonPath("$.price", Matchers.is(500.00)))
                .andExpect(jsonPath("$.startDate", Matchers.is(consultationDate.minusMonths(5).format(formatter))))
                .andExpect(jsonPath("$.endDate", Matchers.is(consultationDate.plusMonths(5).format(formatter))));
    }

    @Test
    public void testGetPriceByProductAndBrandOK() throws Exception {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35455l;

        when(pricesService.getPriceByProductAndBrandAndDate(null, brandId, productId))
                .thenReturn(MockUtils.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1));

        mockMvc.perform(get(baseUrl)
                        .param("brandId", brandId.toString())
                        .param("productId", productId.toString())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product.id", Matchers.is(35455)))
                .andExpect(jsonPath("$.product.name", Matchers.is("Product")))
                .andExpect(jsonPath("$.brand.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand.name", Matchers.is("Zara")))
                .andExpect(jsonPath("$.currency.iso", Matchers.is("EUR")))
                .andExpect(jsonPath("$.price", Matchers.is(500.00)))
                .andExpect(jsonPath("$.startDate", Matchers.is(consultationDate.minusMonths(5).format(formatter))))
                .andExpect(jsonPath("$.endDate", Matchers.is(consultationDate.plusMonths(5).format(formatter))));
    }

    @Test
    public void testGetPriceByProductAndBrandAndDateErrorMissingServletRequestParameter() throws Exception {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        String message = "The value [{0}] cannot be null";
        String formattedMessage = MessageFormat.format(message, "productId");

        when(messageSource.getMessage(ErrorEnum.MISSING_PARAMETER.getMessage(), null, Locale.getDefault())).thenReturn(message);

        mockMvc.perform(get(baseUrl)
                        .param("consultationDate", consultationDate.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MissingServletRequestParameterException))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", Matchers.is(formattedMessage)))
                .andExpect(jsonPath("$.statusCode", Matchers.is(400)));
    }

    @Test
    public void testGetPriceByProductAndBrandAndDateErrorArgumentTypeMismatch() throws Exception {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        String productId = "PRODUCT";

        String message = "The value [{0}] is not valid for parameter [{1}]. This must be of type [{2}]";
        String formattedMessage = MessageFormat.format(message, productId, "productId", "class java.lang.Long");

        when(messageSource.getMessage(ErrorEnum.PARAMETER_TYPE_MISMATCH.getMessage(), null, Locale.getDefault())).thenReturn(message);

        mockMvc.perform(get(baseUrl)
                        .param("consultationDate", consultationDate.toString())
                        .param("brandId", brandId.toString())
                        .param("productId", productId))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentTypeMismatchException))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", Matchers.is(formattedMessage)))
                .andExpect(jsonPath("$.statusCode", Matchers.is(400)));
    }

    @Test
    public void testGetPriceByProductAndBrandAndDateErrorNotFound() throws Exception {

        LocalDateTime consultationDate = LocalDateTime.now();
        Long brandId = 1L;
        Long productId = 35485l;

        String message = "There are no prices defined for product [{0}] on date [{1}]";
        String formattedMessage = MessageFormat.format(message, productId, consultationDate);

        when(pricesService.getPriceByProductAndBrandAndDate(consultationDate, brandId, productId))
                .thenThrow(new PricesNotFoundException(formattedMessage));

        mockMvc.perform(get(baseUrl)
                        .param("consultationDate", consultationDate.toString())
                        .param("brandId", brandId.toString())
                        .param("productId", productId.toString()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof PricesNotFoundException))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", Matchers.is(formattedMessage)))
                .andExpect(jsonPath("$.statusCode", Matchers.is(404)));
    }
}
