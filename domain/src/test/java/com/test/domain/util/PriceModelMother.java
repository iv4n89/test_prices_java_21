package com.test.domain.util;

import java.time.LocalDateTime;

import com.test.domain.model.PriceModel;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.Currency;
import com.test.domain.valueobject.Money;
import com.test.domain.valueobject.PriceList;
import com.test.domain.valueobject.Priority;
import com.test.domain.valueobject.ProductId;

public final class PriceModelMother {
    public static PriceModel from(
            BrandId brandId,
            ProductId productId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            PriceList priceList,
            Priority priority,
            Money price,
            Currency currency) {
        return PriceModel.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();
    }

    public static PriceModel random() {
        return PriceModel.builder()
                .brandId(BrandIdMother.random())
                .productId(ProductIdMother.random())
                .startDate(DateMother.randomLocalDateTime())
                .endDate(DateMother.randomLocalDateTime())
                .priceList(PriceListMother.random())
                .priority(PriorityMother.random())
                .price(MoneyMother.random())
                .currency(CurrencyMother.random())
                .build();
    }

    public static PriceModel randomPriorityOne() {
        return PriceModel.builder()
                .brandId(BrandIdMother.random())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(PriceListMother.random())
                .productId(ProductIdMother.random())
                .priority(PriorityMother.create(1))
                .price(MoneyMother.random())
                .currency(CurrencyMother.random())
                .build();
    }

    public static PriceModel randomPriorityTwo() {
        return PriceModel.builder()
                .brandId(BrandIdMother.random())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(PriceListMother.random())
                .productId(ProductIdMother.random())
                .priority(PriorityMother.create(2))
                .price(MoneyMother.random())
                .currency(CurrencyMother.random())
                .build();
    }
}
