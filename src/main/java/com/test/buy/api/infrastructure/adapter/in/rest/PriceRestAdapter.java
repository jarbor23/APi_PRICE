package com.test.buy.api.infrastructure.adapter.in.rest;

import com.test.buy.api.infrastructure.adapter.in.dto.RequestQueryPrice;
import com.test.buy.api.infrastructure.adapter.in.dto.ResponseQueryPrice;
import com.test.buy.api.infrastructure.adapter.in.handler.PriceHandler;
import com.test.buy.api.infrastructure.adapter.in.handler.common.ErrorDTO;
import com.test.buy.api.infrastructure.adapter.in.handler.common.validator.ApplicationDateOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
@Validated
@Tag(name = "Price.")
public class PriceRestAdapter {
    private final PriceHandler priceHandler;
    @Operation(description = "Get Price by productId and brandId between applicationDate")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "price data.",
                            content = @Content(schema = @Schema(implementation = ResponseQueryPrice.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error in input data.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error in query get none or URL not definida.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("/queryPrice")
    public ResponseEntity<ResponseQueryPrice> queryPrice(
            @Parameter(description = "Application date, format YYYY-mm-DD HH:mm:ss", required = true)
            @RequestParam("applicationDate") @ApplicationDateOrder String applicationDate,
            @Parameter(description = "Product Id", required = true)
            @RequestParam("productId") int productId,
            @Parameter(description = "Brand Id", required = true)
            @RequestParam("brandId") int brandId) {

        ResponseQueryPrice response = priceHandler.getPriceByBrandAndProductAndApplicationDate(
        RequestQueryPrice.builder()
                        .applicationDate(applicationDate)
                        .brandId(brandId)
                        .productId(productId)
                        .build()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
