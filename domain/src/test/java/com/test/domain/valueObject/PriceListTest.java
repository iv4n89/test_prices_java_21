package com.test.domain.valueObject;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.PriceListMother;
import com.test.domain.valueobject.PriceList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DomainTestConfig.class)
public class PriceListTest {

    @Test
    void testPriceListShouldNotBeNull() {
        // Given
        PriceList priceList = PriceListMother.random();

        // Then
        assertNotNull(priceList);
        assertNotNull(priceList.getValue());
    }

    @Test
    void testPriceListShouldThrowExceptionWhenValueIsNull() {
        // Given
        // PriceList will be null

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            PriceList priceList = new PriceList(null);
        });

        // Then
        assertEquals("PriceList value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testPriceListShouldThrowExceptionWhenValueIsNegative() {
        // Given
        // PriceList value will be negative

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            PriceList priceList = new PriceList(-1L);
        });

        // Then
        assertEquals("PriceList value cannot be negative", illegalArgumentException.getMessage());
    }
}
