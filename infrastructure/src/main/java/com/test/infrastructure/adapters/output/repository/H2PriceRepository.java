package com.test.infrastructure.adapters.output.repository;

import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.PriceModel;
import com.test.domain.ports.output.repository.PriceRepository;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;
import com.test.infrastructure.mapper.PriceDataMapper;
import com.test.infrastructure.repository.PriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Primary
@Component("priceRepository")
public final class H2PriceRepository implements PriceRepository {
    private final PriceJpaRepository priceJpaRepository;

    @Override
    public PriceModel findPriceByBrandIdAndProductIdAndApplicationDate(BrandId brandId, ProductId productId, LocalDateTime applicationDate) {
        return priceJpaRepository.findByBrandIdAndProductIdAndStartDateAndEndDate(brandId.getValue(), productId.getValue(), applicationDate)
                .map(PriceDataMapper::toDomainModel)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}
