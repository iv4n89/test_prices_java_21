package com.test.presentation.controllers;

import com.test.application.dto.PriceFinderRequestDto;
import com.test.application.dto.PriceFinderResponseDto;
import com.test.domain.model.PriceModel;
import com.test.domain.ports.input.service.PriceApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/price")
@RequiredArgsConstructor
@RestController
public class PricePostController {
    private final PriceApplicationService priceApplicationService;

    @PostMapping("/find")
    public ResponseEntity<PriceFinderResponseDto> findPrice(@RequestBody PriceFinderRequestDto request) {
        PriceModel price = priceApplicationService.findPrice(request.brandId(), request.productId(), request.date());
        PriceFinderResponseDto response = PriceFinderResponseDto.fromDomainModel(price);

        return ResponseEntity.ok(response);
    }
}
