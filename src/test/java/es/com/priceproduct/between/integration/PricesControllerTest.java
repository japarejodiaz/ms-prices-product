package es.com.priceproduct.between.integration;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class PricesControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @ParameterizedTest(name = "test number case {index} ejecutando con los valores {0} {1} {2} {3} - {argumentsWithNames}")
    @CsvSource({"2020-06-14T10:00:00,35455,1,35.50",
                "2020-06-14T16:00:00,35455,1,25.45",
                "2020-06-14T21:00:00,35455,1,35.50",
                "2020-06-15T10:00:00,35455,1,30.50",
                "2020-06-16T21:00:00,35455,1,38.95"})
    public void testGetPriceByProductAndBrandAtSpecificDateTime(String consultationDate, String productId, String brandId, String expectPrice) throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        testGetPriceByProductAndBrandAtSpecificTimeResult(mockMvc, consultationDate, productId, brandId, expectPrice);
    }

    private void testGetPriceByProductAndBrandAtSpecificTimeResult(MockMvc mockMvc, String consultationDate, String productId, String brandId, String expectPrice) throws Exception {
        /* Conversion de tipos string a los tipos especificos de cada uno de los casos de prueba */
        Long prodId = Long.parseLong(productId);
        Long branId = Long.parseLong(brandId);
        Double priceExpect = Double.parseDouble(expectPrice);

        System.out.println(consultationDate);
        LocalDateTime queryDate = LocalDateTime.parse(consultationDate);

        String url = "/v1/api/prices";
        mockMvc.perform(get(url)
                        .param("consultationDate", queryDate != null ? queryDate.toString() : null)
                        .param("productId", prodId.toString())
                        .param("brandId", branId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product.id", Matchers.is(prodId.intValue())))
                .andExpect(jsonPath("$.brand.id", Matchers.is(branId.intValue())))
                .andExpect(jsonPath("$.product.id", Matchers.is(35455)))
                .andExpect(jsonPath("$.product.name", Matchers.is("JEANS BALLOON FIT")))
                .andExpect(jsonPath("$.brand.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand.name", Matchers.is("Zara")))
                .andExpect(jsonPath("$.currency.iso", Matchers.is("EUR")))
                .andExpect(jsonPath("$.price", Matchers.is(priceExpect)));
    }

}
