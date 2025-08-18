package com.test.presentation.controllers;

import com.test.application.dto.ErrorDto;
import com.test.application.dto.PriceFinderResponseDto;
import com.test.application.facade.PriceFacade;
import com.test.application.mappers.LocalDateTimeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/price")
@RequiredArgsConstructor
@RestController
public class PriceGetController {
    private final PriceFacade priceFacade;

    @Operation(
            summary = "Find price for a product",
            description = "Returns the applicable price for a product in a specific brand at a given date/time"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Price found successfully",
                    content = @Content(schema = @Schema(implementation = PriceFinderResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request parameters",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Price not found for the given criteria",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))
            )
    })
    @GetMapping("/find")
    public ResponseEntity<PriceFinderResponseDto> findPrice(
            @Parameter(description = "Brand identifier", required = true, example = "1")
            @RequestParam
            @NotNull(message = "BrandId is required")
            @Min(value = 1, message = "BrandId must be greater than 0")
            Long brandId,

            @Parameter(description = "Product identifier", required = true, example = "1")
            @RequestParam
            @NotNull(message = "ProductId is required")
            @Min(value = 1, message = "ProductId must be greater than 0")
            Long productId,

            @Parameter(description = "Date and time for price query in ISO-8601 format", required = true, example = "2020-06-14T10:00:00")
            @RequestParam
            @NotNull(message = "Date is required")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            String date
    ) {
        log.debug("Finding price for brandId: {}, productId: {}, date: {}", brandId, productId, date);

        LocalDateTime dateTime = LocalDateTimeMapper.fromString(date);
        PriceFinderResponseDto price = priceFacade.findPrice(brandId, productId, dateTime);

        log.debug("Price found: {}", price);

        return ResponseEntity.ok(price);
    }
}
