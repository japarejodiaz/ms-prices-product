package es.com.priceproduct.between.integration;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PricesControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testGetPriceByProductAndBrandAtDifferentTimes() throws Exception {
        // Test 1
        testGetPriceByProductAndBrandAtSpecificTime(LocalDateTime.of(2020, 06, 14, 10, 0, 0), 35455L, 1L, 35.50);

        // Test 2
        testGetPriceByProductAndBrandAtSpecificTime(LocalDateTime.of(2020, 06, 14, 16, 0, 0), 35455L, 1L, 25.45);

        // Test 3
        testGetPriceByProductAndBrandAtSpecificTime(LocalDateTime.of(2020, 06, 14, 21, 0, 0), 35455L, 1L, 35.50);

        // Test 4
        testGetPriceByProductAndBrandAtSpecificTime(LocalDateTime.of(2020, 06, 15, 10, 0, 0), 35455L, 1L, 30.50);

        // Test 5
        testGetPriceByProductAndBrandAtSpecificTime(LocalDateTime.of(2020, 06, 16, 21, 0, 0), 35455L, 1L, 38.95);

        // Test 6
        testGetPriceByProductAndBrandAtSpecificTime(null, 35455L, 1L, 45.95);
    }

    private void testGetPriceByProductAndBrandAtSpecificTime(LocalDateTime consultationDate, Long productId, Long brandId, Double expectPrice) throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String url = "/v1/api/prices";
        mockMvc.perform(get(url)
                        .param("consultationDate", consultationDate != null ? consultationDate.toString() : null)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product.id", Matchers.is(productId.intValue())))
                .andExpect(jsonPath("$.brand.id", Matchers.is(brandId.intValue())))
                .andExpect(jsonPath("$.product.id", Matchers.is(35455)))
                .andExpect(jsonPath("$.product.name", Matchers.is("JEANS BALLOON FIT")))
                .andExpect(jsonPath("$.brand.id", Matchers.is(1)))
                .andExpect(jsonPath("$.brand.name", Matchers.is("Zara")))
                .andExpect(jsonPath("$.currency.iso", Matchers.is("EUR")))
                .andExpect(jsonPath("$.price", Matchers.is(expectPrice)));
    }






}
