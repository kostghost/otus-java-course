package ru.otus.hw06.atm.cell;

import ru.otus.hw06.atm.NoMoneyException;
import ru.otus.hw06.banknote.Banknote;

// Ячейка хранит в себе некоторое количество банкнот одного типа
public class Cell {
    private final Banknote banknote;
    private int count;

    public Cell(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
    }

    public void insertBills(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        this.count += count;
    }

    public void withdrawBills(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        if (this.count < count) {
            throw new NoMoneyException();
        }
        this.count -= count;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return count;
    }

    public int getSum() {
        return count * banknote.getValue();
    }
}
