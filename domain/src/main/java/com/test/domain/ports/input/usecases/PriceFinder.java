package com.test.domain.ports.input.usecases;

import java.time.LocalDateTime;

import com.test.domain.model.PriceModel;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;

public interface PriceFinder {
    PriceModel findPrice(BrandId brandId, ProductId productId, LocalDateTime date);
}
