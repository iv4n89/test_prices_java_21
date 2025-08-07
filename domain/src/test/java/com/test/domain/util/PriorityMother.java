package com.test.domain.util;

import com.test.domain.valueObject.Priority;

public final class PriorityMother {
    public static Priority create(Integer value) {
        return new Priority(value);
    }

    public static Priority random() {
        final int[] possibleValues = { 0, 1, 2, 3 };
        return new Priority(possibleValues[(int) (Math.random() * possibleValues.length)]);
    }
}
