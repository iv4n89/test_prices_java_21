package com.test.domain.valueObject;

public final class Priority extends PositiveNumericValueObject<Integer> {
    public Priority(Integer value) {
        super(value);
    }

    @Override
    protected String getNullValueErrorMessage() {
        return "Priority value cannot be null";
    }

    @Override
    protected String getNegativeValueErrorMessage() {
        return "Priority value cannot be negative";
    }
}
