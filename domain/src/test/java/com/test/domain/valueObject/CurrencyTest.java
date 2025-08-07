package com.test.domain.valueObject;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.CurrencyMother;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DomainTestConfig.class)
public class CurrencyTest {
    @Test
    void testCurrencyShouldNotBeEmpty() {
        // Given
        Currency currency = CurrencyMother.random();

        // Then
        assertNotNull(currency);
        assertFalse(currency.getValue().isEmpty());
    }

    @Test
    void testCurrencyShouldThrowExceptionWhenValueIsNull() {
        // Given
        // Currency value will be null

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Currency(null);
        });

        // Then
        assertEquals("Currency value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testCurrencyShouldThrowExceptionWhenValueIsEmpty() {
        // Given
        // Currency value will be empty

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Currency("");
        });

        // Then
        assertEquals("Currency value cannot be empty", illegalArgumentException.getMessage());
    }

    @Test
    void testCurrencyShouldThrowExceptionWhenValueIsLessThanThreeCharacters() {
        // Given
        // Currency value will be less than three characters

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Currency("AB");
        });

        // Then
        assertEquals("Currency value must have exactly 3 characters", illegalArgumentException.getMessage());
    }

    @Test
    void testCurrencyShouldThrowExceptionWhenValueIsGreaterThanThreeCharacters() {
        // Given
        // Currency value will have more than three characters

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Currency("ABCD");
        });

        // Then
        assertEquals("Currency value must have exactly 3 characters", illegalArgumentException.getMessage());
    }
}
