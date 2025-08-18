package com.test.presentation.controller;

import com.test.application.dto.PriceFinderRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Sql({"/init-schema.sql"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceGetControllerTest {
    @Autowired
    private WebTestClient client;

    private final String uriToTest = "/price/find";

    @Test
    void testRequestOn14thJune10AM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0, 0));
        Long expectedPriceList = 1L;
        Long expectedBrandId = 1L;
        Long expectedProductId = 35455L;
        String expectedPrice = "35.50 EUR";
        String expectedStartDate = "2020-06-14T00:00:00";
        String expectedEndDate = "2020-12-31T23:59:59";

        // When
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriToTest)
                        .queryParam("brandId", request.brandId())
                        .queryParam("productId", request.productId())
                        .queryParam("date", request.date().toString())
                        .build()
                )
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(expectedPriceList)
                .jsonPath("$.brandId").isEqualTo(expectedBrandId)
                .jsonPath("$.productId").isEqualTo(expectedProductId)
                .jsonPath("$.price").isEqualTo(expectedPrice)
                .jsonPath("$.startDate").isEqualTo(expectedStartDate)
                .jsonPath("$.endDate").isEqualTo(expectedEndDate);
    }

    @Test
    void testRequestOn14thJune16PM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0, 0));
        Long expectedPriceList = 2L;
        Long expectedBrandId = 1L;
        Long expectedProductId = 35455L;
        String expectedPrice = "25.45 EUR";
        String expectedStartDate = "2020-06-14T15:00:00";
        String expectedEndDate = "2020-06-14T18:30:00";

        // When
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriToTest)
                                .queryParam("brandId", request.brandId())
                                .queryParam("productId", request.productId())
                                .queryParam("date", request.date().toString())
                                .build()
                        )
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(expectedPriceList)
                .jsonPath("$.brandId").isEqualTo(expectedBrandId)
                .jsonPath("$.productId").isEqualTo(expectedProductId)
                .jsonPath("$.price").isEqualTo(expectedPrice)
                .jsonPath("$.startDate").isEqualTo(expectedStartDate)
                .jsonPath("$.endDate").isEqualTo(expectedEndDate);
    }

    @Test
    void testRequestOn14thJune21PM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0, 0));
        Long expectedPriceList = 1L;
        Long expectedBrandId = 1L;
        Long expectedProductId = 35455L;
        String expectedPrice = "35.50 EUR";
        String expectedStartDate = "2020-06-14T00:00:00";
        String expectedEndDate = "2020-12-31T23:59:59";

        // When
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriToTest)
                        .queryParam("brandId", request.brandId())
                        .queryParam("productId", request.productId())
                        .queryParam("date", request.date().toString())
                        .build()
                )
                .exchange()
                //Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(expectedPriceList)
                .jsonPath("$.brandId").isEqualTo(expectedBrandId)
                .jsonPath("$.productId").isEqualTo(expectedProductId)
                .jsonPath("$.price").isEqualTo(expectedPrice)
                .jsonPath("$.startDate").isEqualTo(expectedStartDate)
                .jsonPath("$.endDate").isEqualTo(expectedEndDate);
    }

    @Test
    void testRequestOn15thJune10AM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0, 0));
        Long expectedPriceList = 3L;
        Long expectedBrandId = 1L;
        Long expectedProductId = 35455L;
        String expectedPrice = "30.50 EUR";
        String expectedStartDate = "2020-06-15T00:00:00";
        String expectedEndDate = "2020-06-15T11:00:00";

        // When
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriToTest)
                        .queryParam("brandId", request.brandId())
                        .queryParam("productId", request.productId())
                        .queryParam("date", request.date().toString())
                        .build()
                )
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(expectedPriceList)
                .jsonPath("$.brandId").isEqualTo(expectedBrandId)
                .jsonPath("$.productId").isEqualTo(expectedProductId)
                .jsonPath("$.price").isEqualTo(expectedPrice)
                .jsonPath("$.startDate").isEqualTo(expectedStartDate)
                .jsonPath("$.endDate").isEqualTo(expectedEndDate);
    }

    @Test
    void testRequestOn16thJune21PM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0, 0));
        Long expectedPriceList = 4L;
        Long expectedBrandId = 1L;
        Long expectedProductId = 35455L;
        String expectedPrice = "38.95 EUR";
        String expectedStartDate = "2020-06-15T16:00:00";
        String expectedEndDate = "2020-12-31T23:59:59";

        // When
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriToTest)
                        .queryParam("brandId", request.brandId())
                        .queryParam("productId", request.productId())
                        .queryParam("date", request.date().toString())
                        .build()
                )
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(expectedPriceList)
                .jsonPath("$.brandId").isEqualTo(expectedBrandId)
                .jsonPath("$.productId").isEqualTo(expectedProductId)
                .jsonPath("$.price").isEqualTo(expectedPrice)
                .jsonPath("$.startDate").isEqualTo(expectedStartDate)
                .jsonPath("$.endDate").isEqualTo(expectedEndDate);
    }

    @Test
    void testRequestNotFound() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2021, 6, 16, 21, 0, 0));

        // When
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriToTest)
                        .queryParam("brandId", request.brandId())
                        .queryParam("productId", request.productId())
                        .queryParam("date", request.date().toString())
                        .build()
                )
                .exchange()
                // Then
                .expectStatus().isNotFound();
    }
}
