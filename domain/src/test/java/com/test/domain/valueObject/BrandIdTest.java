package com.test.domain.valueObject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.BrandIdMother;
import com.test.domain.valueobject.BrandId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DomainTestConfig.class)
class BrandIdTest {

    @Test
    void testBrandIdShouldNotBeNull() {
        // Given
        // BrandId should not be null

        BrandId brandId = BrandIdMother.random();

        // Then
        assertNotNull(brandId, "BrandId value cannot be null");
    }

    @Test
    void testBrandIdShouldThrowExceptionWhenValueIsNull() {
        // Given
        // BrandId value will be null

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            BrandId brandId = new BrandId(null);
        });

        // then
        assertEquals("BrandId value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testBrandIdShouldThrowExceptionWhenValueIsNegative() {
        // Given
        Long value = -1L;

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            BrandId brandId = new BrandId(value);
        });

        // then
        assertEquals("BrandId value cannot be negative", illegalArgumentException.getMessage());
    }
}
