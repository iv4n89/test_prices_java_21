package com.test.domain.valueobject;

public final class Currency extends StringValueObject {
    public Currency(String value) {
        super(value);
    }

    @Override
    protected void isValid() {
        super.isValid();
        hasNotThreeChars();
    }

    @Override
    public String getNullValueErrorMessage() {
        return "Currency value cannot be null";
    }

    @Override
    public String getEmptyValueErrorMessage() {
        return "Currency value cannot be empty";
    }

    private void hasNotThreeChars() {
        if (value.length() != 3) {
            throw new IllegalArgumentException("Currency value must have exactly 3 characters");
        }
    }
}
