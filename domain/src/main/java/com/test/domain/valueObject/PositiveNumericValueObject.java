package com.test.domain.valueObject;

public abstract class PositiveNumericValueObject<T> extends ValueObject<T> {
    protected PositiveNumericValueObject(T value) {
        super(value);
    }

    protected abstract String getNullValueErrorMessage();
    protected abstract String getNegativeValueErrorMessage();

    @Override
    protected void isValid() {
        isNotNull();
        isNotNegative();
    }

    protected void isNotNull() {
        if (value == null) {
            throw new IllegalArgumentException(getNullValueErrorMessage());
        }
    }

    protected void isNotNegative() {
        if (value instanceof Long longValue && longValue < 0 ||
            value instanceof Integer intValue && intValue < 0 ||
            value instanceof Double doubleValue && doubleValue < 0 ||
            value instanceof Float floatValue && floatValue < 0) {
            throw new IllegalArgumentException(getNegativeValueErrorMessage());
        }
    }
}
