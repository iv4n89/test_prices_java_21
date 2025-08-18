package com.test.infrastructure.mapper;

import com.test.domain.model.PriceModel;
import com.test.domain.util.*;
import com.test.infrastructure.InfrastructureTestConfig;
import com.test.infrastructure.entity.PriceEntity;
import com.test.infrastructure.repository.PriceJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(classes = InfrastructureTestConfig.class)
class PriceDataMapperTest {
    @Autowired
    @Qualifier("priceJpaRepositoryTest")
    private PriceJpaRepository priceJpaRepository;

    @Autowired
    @Qualifier("priceMapperTest")
    private EntityMapper<PriceEntity, PriceModel> priceDataMapper;

    @Test
    void testMapperShouldMapToDomainModel() {
        // Given
        PriceDataMapper mapper = new PriceDataMapper();
        PriceEntity priceEntity =
                PriceEntity.builder()
                        .brandId(BrandIdMother.random().getValue())
                        .productId(ProductIdMother.random().getValue())
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plusDays(1))
                        .price(MoneyMother.random().getValue())
                        .priceList(PriceListMother.random().getValue())
                        .curr(CurrencyMother.random().getValue())
                        .priority(PriorityMother.random().getValue())
                        .build();

        // When
        PriceModel priceModel = mapper.toDomain(priceEntity);

        // Then
        assertNotNull(priceModel);
        assertEquals(priceEntity.getBrandId(), priceModel.getBrandId().getValue());
        assertEquals(priceEntity.getProductId(), priceModel.getProductId().getValue());
        assertEquals(priceEntity.getStartDate(), priceModel.getStartDate());
        assertEquals(priceEntity.getEndDate(), priceModel.getEndDate());
        assertEquals(priceEntity.getPrice(), priceModel.getPrice().getValue());
        assertEquals(priceEntity.getPriceList(), priceModel.getPriceList().getValue());
        assertEquals(priceEntity.getCurr(), priceModel.getCurrency().getValue());
        assertEquals(priceEntity.getPriority(), priceModel.getPriority().getValue());
    }

    @Test
    void testMapperShouldMapToEntity() {
        // Given
        PriceDataMapper mapper = new PriceDataMapper();
        PriceModel priceModel = PriceModelMother.random();

        // When
        PriceEntity priceEntity = mapper.toEntity(priceModel);

        // Then
        assertNotNull(priceEntity);
        assertEquals(priceModel.getBrandId().getValue(), priceEntity.getBrandId());
        assertEquals(priceModel.getProductId().getValue(), priceEntity.getProductId());
        assertEquals(priceModel.getStartDate(), priceEntity.getStartDate());
        assertEquals(priceModel.getEndDate(), priceEntity.getEndDate());
        assertEquals(priceModel.getPrice().getValue(), priceEntity.getPrice());
        assertEquals(priceModel.getPriceList().getValue(), priceEntity.getPriceList());
        assertEquals(priceModel.getCurrency().getValue(), priceEntity.getCurr());
        assertEquals(priceModel.getPriority().getValue(), priceEntity.getPriority());
    }
}
