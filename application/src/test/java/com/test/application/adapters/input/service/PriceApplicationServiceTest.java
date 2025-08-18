package com.test.application.adapters.input.service;

import com.test.application.PriceApplicationTestConfig;
import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.PriceModel;
import com.test.domain.ports.input.service.PriceApplicationService;
import com.test.domain.ports.input.usecases.PriceFinder;
import com.test.domain.ports.output.repository.PriceRepository;
import com.test.domain.util.PriceModelMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = PriceApplicationTestConfig.class)
public class PriceApplicationServiceTest {
    @Autowired
    @Qualifier("priceApplicationServiceTest")
    private PriceApplicationService priceApplicationService;

    @Autowired
    @Qualifier("priceFinderTest")
    private PriceFinder priceFinder;

    @Autowired
    @Qualifier("priceRepositoryTest")
    private PriceRepository priceRepository;

    @Test
    void testFindAPrice() {
        // Given
        PriceModel expected = PriceModelMother.random();
        when(priceRepository.findApplicablePrice(any(), any(), any())).thenReturn(Optional.of(expected));

        // When
        PriceModel actual = priceApplicationService.findPrice(1L, 1L, LocalDateTime.now());

        // Then
        verify(priceRepository).findApplicablePrice(any(), any(), any());
        verifyNoMoreInteractions(priceRepository);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindAPriceAndThrowExceptionWhenNotFound() {
        // Given
        LocalDateTime date = LocalDateTime.now();
        when(priceRepository.findApplicablePrice(any(), any(), any())).thenThrow(new PriceNotFoundException("Price not found"));

        // When
        PriceNotFoundException priceNotFoundException = assertThrows(PriceNotFoundException.class, () -> priceApplicationService.findPrice(1L, 1L, date));

        // Then
        verify(priceRepository, times(1)).findApplicablePrice(any(), any(), any());
        verifyNoMoreInteractions(priceRepository);

        assertNotNull(priceNotFoundException);
        assertEquals("Price not found", priceNotFoundException.getMessage());
    }
}
