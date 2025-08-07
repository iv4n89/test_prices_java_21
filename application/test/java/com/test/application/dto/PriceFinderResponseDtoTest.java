package com.test.application.dto;

import com.test.application.PriceApplicationTestConfig;
import com.test.domain.util.*;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.Money;
import com.test.domain.valueObject.PriceList;
import com.test.domain.valueObject.ProductId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PriceApplicationTestConfig.class)
public class PriceFinderResponseDtoTest {
    @Test
    void testPriceFinderResponseDtoShouldBeCreated() {
        // Given
        ProductId productId = ProductIdMother.random();
        BrandId brandId = BrandIdMother.random();
        PriceList priceList = PriceListMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Money price = MoneyMother.random();

        // When
        PriceFinderResponseDto priceFinderResponseDto =
                    new PriceFinderResponseDto(
                            productId.getValue(),
                            brandId.getValue(),
                            priceList.getValue(),
                            startDate,
                            endDate,
                            price.getValue().toPlainString()
                    );

        // Then
        assertNotNull(priceFinderResponseDto);
        assertEquals(productId.getValue(), priceFinderResponseDto.getProductId());
        assertEquals(brandId.getValue(), priceFinderResponseDto.getBrandId());
        assertEquals(priceList.getValue(), priceFinderResponseDto.getPriceList());
        assertEquals(startDate, priceFinderResponseDto.getStartDate());
        assertEquals(endDate, priceFinderResponseDto.getEndDate());
        assertEquals(price.getValue().toPlainString(), priceFinderResponseDto.getPrice());
    }

    @Test
    void testPriceFinderResponseDtoShouldBeBuilt() {
        // Given
        ProductId productId = ProductIdMother.random();
        BrandId brandId = BrandIdMother.random();
        PriceList priceList = PriceListMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Money price = MoneyMother.random();

        // When
        PriceFinderResponseDto priceFinderResponseDto =
                PriceFinderResponseDto.builder()
                        .productId(productId.getValue())
                        .brandId(brandId.getValue())
                        .priceList(priceList.getValue())
                        .startDate(startDate)
                        .endDate(endDate)
                        .price(price.getValue().toPlainString())
                        .build();

        // Then
        assertNotNull(priceFinderResponseDto);
        assertEquals(productId.getValue(), priceFinderResponseDto.getProductId());
        assertEquals(brandId.getValue(), priceFinderResponseDto.getBrandId());
        assertEquals(priceList.getValue(), priceFinderResponseDto.getPriceList());
        assertEquals(startDate, priceFinderResponseDto.getStartDate());
        assertEquals(endDate, priceFinderResponseDto.getEndDate());
        assertEquals(price.getValue().toPlainString(), priceFinderResponseDto.getPrice());
    }
}
