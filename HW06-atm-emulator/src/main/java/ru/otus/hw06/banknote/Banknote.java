package ru.otus.hw06.banknote;

import java.util.Objects;

public abstract class Banknote implements Comparable<Banknote> {
    // Счтаем, что банкомат работает только с целыми числами
    private int value;

    protected Banknote(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Banknote o) {
        var otherValue = o.getValue();
        return -Integer.compare(this.getValue(), otherValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Banknote banknote = (Banknote) o;
        return value == banknote.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Currency getCurrency() {
        return Currency.NOT_SPECIFIED;
    }
}
