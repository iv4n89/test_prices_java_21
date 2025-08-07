package com.test.domain.util;

import com.test.domain.valueObject.PriceList;

public final class PriceListMother {
    public static PriceList create(Long value) {
        return new PriceList(value);
    }

    public static PriceList random() {
        return new PriceList(MotherCreator.random().number().randomNumber());
    }
}
