package com.test.application.facade;

import com.test.application.PriceApplicationTestConfig;
import com.test.application.dto.PriceFinderResponseDto;
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
public class PriceFacadeTest {
    @Autowired
    @Qualifier("priceApplicationServiceFacadeTest")
    private PriceFacade priceFacade;

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
        PriceModel expectedModel = PriceModelMother.random();
        PriceFinderResponseDto expected = PriceFinderResponseDto.fromDomainModel(expectedModel);
        when(priceRepository.findApplicablePrice(any(), any(), any())).thenReturn(Optional.of(expectedModel));

        // When
        PriceFinderResponseDto actual = priceFacade.findPrice(1L, 1L, LocalDateTime.now());

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
        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> priceFacade.findPrice(1L, 1L, date));

        // Then
        assertNotNull(exception);
        assertEquals("Price not found", exception.getMessage());
    }
}
