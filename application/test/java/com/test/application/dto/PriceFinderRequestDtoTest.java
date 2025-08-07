package com.test.application.dto;

import com.test.application.PriceApplicationTestConfig;
import com.test.domain.util.BrandIdMother;
import com.test.domain.util.DateMother;
import com.test.domain.util.ProductIdMother;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PriceApplicationTestConfig.class)
public class PriceFinderRequestDtoTest {
    @Test
    void testPriceFinderRequestDtoShouldBeCreated() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime date = DateMother.randomLocalDateTime();

        // When
        PriceFinderRequestDto priceFinderRequestDto = new PriceFinderRequestDto(brandId.getValue(), productId.getValue(), date);

        // Then
        assertNotNull(priceFinderRequestDto);
        assertEquals(brandId.getValue(), priceFinderRequestDto.brandId());
        assertEquals(productId.getValue(), priceFinderRequestDto.productId());
        assertEquals(date, priceFinderRequestDto.date());
    }
}
