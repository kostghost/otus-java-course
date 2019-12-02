package ru.otus.hw06.currency;


import java.util.Objects;

public abstract class Currency implements Comparable<Currency> {
    // Счтаем, что банкомат работает только с целыми числами
    private int value;

    protected Currency(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return "";
    }

    public String getShortName() {
        return "";
    }

    public int compareTo(Currency o) {
        var otherValue = o.getValue();
        return Integer.compare(this.getValue(), otherValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Currency currency = (Currency) o;
        return value == currency.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
