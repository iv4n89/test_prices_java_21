package com.test.infrastructure.adapters.output.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.PriceModel;
import com.test.domain.ports.output.repository.PriceRepository;
import com.test.domain.util.*;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;
import com.test.infrastructure.InfrastructureTestConfig;
import com.test.infrastructure.entity.PriceEntity;
import com.test.infrastructure.repository.PriceJpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = InfrastructureTestConfig.class)
class H2PriceRepositoryTest {
    @Autowired
    @Qualifier("priceRepositoryTest")
    private PriceRepository priceRepository;

    @Autowired
    @Qualifier("priceJpaRepositoryTest")
    private PriceJpaRepository priceJpaRepository;

    @Test
    void testFindPriceByBrandIdAndProductIdAndApplicationDate() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime applicationDate = LocalDateTime.now();
        PriceEntity expected =
                PriceEntity.builder()
                        .brandId(brandId.getValue())
                        .productId(productId.getValue())
                        .startDate(applicationDate.minusDays(1))
                        .endDate(applicationDate.plusDays(1))
                        .price(MoneyMother.random().getValue())
                        .curr(CurrencyMother.random().getValue())
                        .priceList(PriceListMother.random().getValue())
                        .priority(PriorityMother.random().getValue())
                        .build();
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateAndEndDate(
                brandId.getValue(), productId.getValue(), applicationDate))
                .thenReturn(Optional.of(expected));

        // When
        PriceModel actual =
                priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
                        brandId, productId, applicationDate);

        // Then
        assertNotNull(actual);
        assertEquals(expected.getBrandId(), actual.getBrandId().getValue());
        assertEquals(expected.getProductId(), actual.getProductId().getValue());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getPrice(), actual.getPrice().getValue());
        assertEquals(expected.getCurr(), actual.getCurrency().getValue());
        assertEquals(expected.getPriceList(), actual.getPriceList().getValue());
        assertEquals(expected.getPriority(), actual.getPriority().getValue());

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndStartDateAndEndDate(
                        brandId.getValue(), productId.getValue(), applicationDate);
        verifyNoMoreInteractions(priceJpaRepository);
    }

    @Test
    void testFindPriceByBrandIdAndProductIdAndApplicationDateWithNoResults() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime applicationDate = LocalDateTime.now();
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateAndEndDate(
                brandId.getValue(), productId.getValue(), applicationDate))
                .thenThrow(new PriceNotFoundException("Price not found"));

        // When
        PriceNotFoundException priceNotFoundException =
                assertThrows(
                        PriceNotFoundException.class,
                        () ->
                                priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
                                        brandId, productId, applicationDate));

        // Then
        assertNotNull(priceNotFoundException);
        assertEquals("Price not found", priceNotFoundException.getMessage());

        verify(priceJpaRepository, times(1))
                .findByBrandIdAndProductIdAndStartDateAndEndDate(
                        brandId.getValue(), productId.getValue(), applicationDate);
        verifyNoMoreInteractions(priceJpaRepository);
    }
}
