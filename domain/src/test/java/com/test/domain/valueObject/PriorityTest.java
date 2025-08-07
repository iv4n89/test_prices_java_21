package com.test.domain.valueObject;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.PriorityMother;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DomainTestConfig.class)
public class PriorityTest {
    @Test
    void testPriorityShouldNotBeNull() {
        // Given
        Priority priority = PriorityMother.random();

        // Then
        assertNotNull(priority);
        assertNotNull(priority.getValue());
    }

    @Test
    void testPriorityShouldThrowExceptionWhenValueIsNull() {
        // Given
        // Priority value will be null

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Priority(null);
        });

        // Then
        assertEquals("Priority value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testPriorityShouldThrowExceptionWhenValueIsNegative() {
        // Given
        // Priority value will be negative

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Priority(-1);
        });

        // Then
        assertEquals("Priority value cannot be negative", illegalArgumentException.getMessage());
    }
}
