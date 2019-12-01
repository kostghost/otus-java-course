package ru.otus.hw06.currency.rouble;

import java.util.Objects;

import ru.otus.hw06.currency.Currency;

// Возможно, это слишком переигрывание в ООП и можно было ограничиться просто одним классом Rouble,
// не делая релизации абстрактного класса
// Но так, как мне кажется, подчеркивается, что деньги - что-то внешнее и неизменяемое
// в ходе работы банкомата.
public abstract class Rouble implements Currency, Comparable<Currency> {

    private int value;

    Rouble(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return "Рубль";
    }

    @Override
    public String getShortName() {
        return "руб.";
    }

    @Override
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
        Rouble rouble = (Rouble) o;
        return value == rouble.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
