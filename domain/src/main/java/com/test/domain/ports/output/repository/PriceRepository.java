package com.test.domain.ports.output.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.test.domain.model.PriceModel;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;

public interface PriceRepository {
    Optional<PriceModel> findApplicablePrice(BrandId brandId, ProductId productId, LocalDateTime applicationDate);
}
