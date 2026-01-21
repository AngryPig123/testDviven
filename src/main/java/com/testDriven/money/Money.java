package com.testDriven.money;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : com.testDriven.money.Money
 * author         : AngryPig123
 * date           : 26. 1. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 18.        AngryPig123       최초 생성
 */
public class Money implements Expression {

    protected int amount;
    protected String currency;

    public boolean equals(Object obj) {
        if (obj instanceof Money money) {
            return amount == money.amount && this.currency().equals(money.currency());
        } else {
            return false;
        }
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public String currency() {
        return currency;
    }

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    @Override
    public String toString() {
        return "com.testDriven.money.Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
