package com.test.application.facade;

import com.test.application.dto.PriceFinderResponseDto;
import com.test.domain.model.PriceModel;
import com.test.domain.ports.input.service.PriceApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceFacade {
    private final PriceApplicationService priceApplicationService;

    public PriceFinderResponseDto findPrice(Long brandId, Long productId, LocalDateTime date) {
        PriceModel priceModel = priceApplicationService.findPrice(brandId, productId, date);
        return PriceFinderResponseDto.fromDomainModel(priceModel);
    }
}
