package com.test.domain.ports.output.repository;

import java.time.LocalDateTime;

import com.test.domain.model.PriceModel;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;

public interface PriceRepository {
    PriceModel findPriceByBrandIdAndProductIdAndApplicationDate(BrandId brandId, ProductId productId, LocalDateTime applicationDate);
}
