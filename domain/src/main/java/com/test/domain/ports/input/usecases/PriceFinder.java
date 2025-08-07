package com.test.domain.ports.input.usecases;

import java.time.LocalDateTime;

import com.test.domain.model.PriceModel;

public interface PriceFinder {
    PriceModel findPrice(Long brandId, Long productId, LocalDateTime date);
}
