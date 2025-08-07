package com.test.domain.util;

public final class WordMother {
    public static String random() {
        return MotherCreator.random().lorem().word();
    }

    public static String random(int length) {
        return MotherCreator.random().lorem().fixedString(length);
    }
}
