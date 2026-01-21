package com.testDriven.money;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : TestDrivenCLass
 * author         : AngryPig123
 * date           : 26. 1. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 18.        AngryPig123       최초 생성
 */
public class MoneyTest {
    /*
        1) 통화(Currency)를 값의 일부로 취급한다
            •	돈은 단순 숫자가 아니라 (amount, currency) 로 모델링되어야 한다.
                    •	같은 금액이라도 통화가 다르면 서로 다른 값이다.
            •	예: 5 USD != 5 CHF
            •	동등성(equals)은 금액 + 통화 기준으로 판단해야 한다.

        2) 연산 결과는 통화를 보존해야 한다 (동일 통화 내)
            •	곱셈 같은 연산은 통화를 유지한 채 금액만 바뀐다.
                    •	예: 5 USD * 2 = 10 USD
            •	예: 5 CHF * 2 = 10 CHF

        3) 서로 다른 통화의 합은 “즉시 계산”이 아니라 “표현(com.testDriven.money.Expression)”으로 남긴다
            •	USD + CHF 같은 연산은 그 자리에서 단일 Money로 확정되지 않는다.
            •	대신 “합(com.testDriven.money.Sum)” 같은 표현 객체(com.testDriven.money.Expression) 로 만들어서,
            •	나중에 “어느 통화로 환산할지”를 알고 있는 컴포넌트가 계산한다.

        4) 환산은 Bank가 책임진다 (환율 테이블 필요)
            •	서로 다른 통화를 한 통화로 “줄이기(reduce)” 위해서는 환율(rate) 이 필요하다.
                    •	환율은 com.testDriven.money.Bank(또는 그에 준하는 객체)가 알고/관리한다.
            •	예: com.testDriven.money.Bank.addRate("CHF","USD", 2) → (책 예제에선 2:1 형태로 등장)
                    •	com.testDriven.money.Bank.reduce(expression, "USD") 처럼 목표 통화(target currency) 를 지정해 결과를 얻는다.

        5) 환산은 목표 통화가 무엇인지에 따라 결과가 달라진다
            •	같은 합이라도 USD로 reduce하느냐 CHF로 reduce하느냐에 따라 결과가 달라질 수 있다.
            •	즉, 계산은 항상 목표 통화가 명시되어야 한다.

        6) “같은 통화로의 환산”은 항등 환율(1)을 취급한다
            •	USD -> USD 같은 경우는 환율이 1로 동작해야 한다(또는 특별취급 없이 자연스럽게 처리).
                    •	이 요구가 있어야 단일 통화 연산도 동일한 reduce 경로로 통일하기 쉽다.
    */

    @Test
    void testMultiplication() {

        Money five = Money.dollar(5);
        Assertions.assertEquals(Money.dollar(10), five.times(2));
        Assertions.assertEquals(Money.dollar(15), five.times(3));
        Assertions.assertEquals(five, Money.dollar(5));

    }

    @Test
    void testFrancMultiplication() {

        Money five = Money.franc(5);
        Assertions.assertEquals(Money.franc(10), five.times(2));
        Assertions.assertEquals(Money.franc(15), five.times(3));
        Assertions.assertEquals(five, Money.franc(5));

    }

    @Test
    void testEquality() {
        Assertions.assertEquals(Money.dollar(5), Money.dollar(5));
        Assertions.assertEquals(Money.franc(5), Money.franc(5));
        Assertions.assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    @Test
    void testCurrency() {
        Assertions.assertEquals("USD", Money.dollar(2).currency());
        Assertions.assertEquals("CHF", Money.franc(2).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        Assertions.assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        Assertions.assertEquals(five, sum.augend);
        Assertions.assertEquals(five, sum.addend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        Assertions.assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money usd = bank.reduce(Money.dollar(1), "USD");
        Assertions.assertEquals(Money.dollar(1), usd);
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        Assertions.assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        Assertions.assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFranc = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Assertions.assertEquals(Money.dollar(10), bank.reduce(fiveBucks.plus(tenFranc), "USD"));
    }

    @Test
    void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        Assertions.assertEquals(Money.dollar(15), result);
    }

    @Test
    void testSumTimes() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        Assertions.assertEquals(Money.dollar(20), result);
    }

    @Test
    void testPlusSameCurrencyReturnsMoney() {
        Expression sum = Money.dollar(1).plus(Money.dollar(1));
        Assertions.assertInstanceOf(Sum.class, sum);
    }

}
