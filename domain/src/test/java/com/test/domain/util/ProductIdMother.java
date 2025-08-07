package com.test.domain.util;

import com.test.domain.valueObject.ProductId;

public class ProductIdMother {
    public static ProductId create(Long value) {
        return new ProductId(value);
    }

    public static ProductId random() {
        return create(MotherCreator.random().number().randomNumber());
    }
}
