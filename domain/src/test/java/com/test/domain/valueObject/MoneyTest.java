package com.test.domain.valueObject;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.MoneyMother;
import com.test.domain.valueobject.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DomainTestConfig.class)
public class MoneyTest {
    @Test
    void testMoneyShouldNotBeNull() {
        // Given
        Money money = MoneyMother.random();

        // Then
        assertNotNull(money);
        assertNotNull(money.getValue());
    }

    @Test
    void testMoneyShouldAddAmount() {
        // Given
        Money money = MoneyMother.one();
        BigDecimal toAdd = BigDecimal.ONE;
        Money toAdd2 = MoneyMother.one();

        // When
        Money newMoney = money.add(toAdd);

        // Then
        assertEquals(newMoney.getValue(), BigDecimal.valueOf(2));

        // When
        Money newMoney2 = newMoney.add(toAdd2);

        // Then
        assertEquals(newMoney2.getValue(), BigDecimal.valueOf(3));
    }

    @Test
    void testMoneyShouldSubtractAmount() {
        // Given
        Money money = MoneyMother.create(BigDecimal.valueOf(5));
        BigDecimal toSubtract = BigDecimal.ONE;
        Money toSubtract2 = MoneyMother.one();

        // When
        Money newMoney = money.substract(toSubtract);
        Money newMoney2 = newMoney.substract(toSubtract2);

        // Then
        assertEquals(newMoney.getValue(), BigDecimal.valueOf(4));
        assertEquals(newMoney2.getValue(), BigDecimal.valueOf(3));
    }

    @Test
    void testMoneyShouldMultiplyAmount() {
        // Given
        Money money = MoneyMother.create(BigDecimal.valueOf(5));
        BigDecimal toMultiply = BigDecimal.valueOf(2);

        // When
        Money newMoney = money.multiply(toMultiply);

        // Then
        assertEquals(newMoney.getValue(), BigDecimal.valueOf(10));
    }
}
