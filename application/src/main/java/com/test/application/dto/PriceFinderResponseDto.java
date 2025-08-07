package com.test.application.dto;

import com.test.domain.model.PriceModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public final class PriceFinderResponseDto implements Response {
    private final Long productId;
    private final Long brandId;
    private final Long priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String price;

    public static PriceFinderResponseDto fromDomainModel(PriceModel price) {
        return PriceFinderResponseDto.builder()
                .productId(price.getProductId().getValue())
                .brandId(price.getBrandId().getValue())
                .priceList(price.getPriceList().getValue())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice().getValue().toPlainString().concat(" ").concat(price.getCurrency().getValue()))
                .build();
    }
}
