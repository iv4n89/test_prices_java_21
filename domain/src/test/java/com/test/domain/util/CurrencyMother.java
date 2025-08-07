package com.test.domain.util;

import com.test.domain.valueobject.Currency;

public final class CurrencyMother {
    public static Currency create(String value) {
        return new Currency(value);
    }

    public static Currency random() {
        return create(WordMother.random(3));
    }
}
