package com.test.domain.ports.input.service;

import java.time.LocalDateTime;

import com.test.domain.model.PriceModel;

public interface PriceApplicationService {
    PriceModel findPrice(Long brandId, Long productId, LocalDateTime date);
}
