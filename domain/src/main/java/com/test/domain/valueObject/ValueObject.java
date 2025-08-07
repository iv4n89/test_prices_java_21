package com.test.domain.valueobject;

import java.util.Objects;

public abstract class ValueObject<T> {
    protected final T value;

    protected ValueObject(T value) {
        this.value = value;
        isValid();
    }

    public T getValue() {
        return value;
    }

    protected abstract void isValid();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueObject<?> that = (ValueObject<?>) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
