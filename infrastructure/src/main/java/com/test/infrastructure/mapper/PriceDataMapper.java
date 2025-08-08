package com.test.infrastructure.mapper;

import com.test.domain.model.PriceModel;
import com.test.domain.valueObject.*;
import com.test.infrastructure.entity.PriceEntity;

public final class PriceDataMapper {
    public static PriceModel toDomainModel(PriceEntity priceEntity) {
        return PriceModel.builder()
                .brandId(new BrandId(priceEntity.getBrandId()))
                .productId(new ProductId(priceEntity.getProductId()))
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priceList(new PriceList(priceEntity.getPriceList()))
                .price(new Money(priceEntity.getPrice()))
                .currency(new Currency(priceEntity.getCurr()))
                .priority(new Priority(priceEntity.getPriority()))
                .build();
    }

    public static PriceEntity toEntity(PriceModel priceModel) {
        return PriceEntity.builder()
                .brandId(priceModel.getBrandId().getValue())
                .productId(priceModel.getProductId().getValue())
                .startDate(priceModel.getStartDate())
                .endDate(priceModel.getEndDate())
                .priceList(priceModel.getPriceList().getValue())
                .price(priceModel.getPrice().getValue())
                .curr(priceModel.getCurrency().getValue())
                .priority(priceModel.getPriority().getValue())
                .build();
    }
}
