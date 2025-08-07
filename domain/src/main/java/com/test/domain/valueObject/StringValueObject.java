package com.test.domain.valueobject;

public abstract class StringValueObject extends ValueObject<String> {
    protected StringValueObject(String value) {
        super(value);
    }

    @Override
    protected void isValid() {
        isNotNull();
        isNotEmpty();
    }

    public abstract String getNullValueErrorMessage();

    public abstract String getEmptyValueErrorMessage();

    protected void isNotNull() {
        if (value == null) {
            throw new IllegalArgumentException(getNullValueErrorMessage());
        }
    }

    protected void isNotEmpty() {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(getEmptyValueErrorMessage());
        }
    }
}
