package ru.otus.hw06.atm.cell;

import ru.otus.hw06.currency.Bundle;
import ru.otus.hw06.currency.Currency;

// Ячейка хранит в себе некоторое количество банкнот одного типа
public class Cell implements Comparable {
    private Bundle bundle;
    private Currency currency;

    public Cell(Currency currency, int count) {
        this.currency = currency;
        this.bundle = new Bundle();
        this.bundle.add(currency, count);
    }

    public void insertBills(int count) {
        bundle.add(currency, count);
    }

    public void withdrawBills(int count) {
        bundle.withdraw(currency, count);
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getCount() {
        return bundle.getCountByCurrency(currency);
    }

    public int getSum() {
        return bundle.sum();
    }

    // небольшой хак
    @Override
    public int compareTo(Object o) {
        if (o instanceof Cell) {
            return -getCurrency().compareTo(((Cell) o).getCurrency());
        } else {
            throw new IllegalArgumentException("Можно сравнивать ячейки только друг с другом");
        }
    }


}
