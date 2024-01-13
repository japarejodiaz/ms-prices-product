package es.com.priceproduct.between.controller;

import es.com.priceproduct.between.dto.response.ErrorResponse;
import es.com.priceproduct.between.dto.response.PricesResponse;
import es.com.priceproduct.between.service.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/api")
public class PricesController {

  @Autowired
  PricesService pricesService;


  @Operation(summary = "Check the price of a product for a specific date", tags = { "prices" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Ok", content = {
          @Content(schema = @Schema(implementation = PricesResponse.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = {
          @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", description = "There are no defined prices for that product", content = {
              @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", description = "An unexpected error occurred", content = {
              @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
  @GetMapping("/prices")
  public ResponseEntity<PricesResponse> getPriceByProductAndBrandAndDate(@RequestParam(name = "consultationDate", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime consultationDate,
                                                        @RequestParam(name = "brandId",required = true) @NotNull Long brandId,
                                                        @RequestParam(name = "productId") @NotNull Long productId) {

    PricesResponse prices = pricesService.getPriceByProductAndBrandAndDate(consultationDate,brandId,productId);

    return ResponseEntity.ok().body(prices);

  }

}
