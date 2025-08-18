package com.test.infrastructure.mapper;

import com.test.domain.model.PriceModel;
import com.test.domain.valueObject.*;
import com.test.infrastructure.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public final class PriceDataMapper implements EntityMapper<PriceEntity, PriceModel> {

    @Override
    public PriceModel toDomain(PriceEntity entity) {
        if (entity == null) {
            return null;
        }

        return PriceModel.builder()
                .brandId(new BrandId(entity.getBrandId()))
                .productId(new ProductId(entity.getProductId()))
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(new PriceList(entity.getPriceList()))
                .price(new Money(entity.getPrice()))
                .currency(new Currency(entity.getCurr()))
                .priority(new Priority(entity.getPriority()))
                .build();
    }

    @Override
    public PriceEntity toEntity(PriceModel domain) {
        if (domain == null) {
            return null;
        }

        return PriceEntity.builder()
                .brandId(domain.getBrandId().getValue())
                .productId(domain.getProductId().getValue())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .priceList(domain.getPriceList().getValue())
                .price(domain.getPrice().getValue())
                .curr(domain.getCurrency().getValue())
                .priority(domain.getPriority().getValue())
                .build();
    }
}
