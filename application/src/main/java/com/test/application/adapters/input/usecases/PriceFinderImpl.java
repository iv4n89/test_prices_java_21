package com.test.application.adapters.input.usecases;

import com.test.domain.model.PriceModel;
import com.test.domain.ports.input.usecases.PriceFinder;
import com.test.domain.ports.output.repository.PriceRepository;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service("priceFinderImpl")
@Primary
public final class PriceFinderImpl implements PriceFinder {
    private final PriceRepository priceRepository;

    @Override
    public PriceModel findPrice(Long brandId, Long productId, LocalDateTime date) {
        final BrandId brandIdValueObject = new BrandId(brandId);
        final ProductId productIdValueObject = new ProductId(productId);

        return priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(brandIdValueObject, productIdValueObject, date);
    }
}
