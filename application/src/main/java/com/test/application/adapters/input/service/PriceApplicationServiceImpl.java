package com.test.application.adapters.input.service;

import com.test.domain.model.PriceModel;
import com.test.domain.ports.input.service.PriceApplicationService;
import com.test.domain.ports.input.usecases.PriceFinder;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Primary
@Service("priceApplicationServiceImpl")
public final class PriceApplicationServiceImpl implements PriceApplicationService {
    private final PriceFinder priceFinder;

    @Override
    public PriceModel findPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceFinder.findPrice(new BrandId(brandId), new ProductId(productId), date);
    }
}
