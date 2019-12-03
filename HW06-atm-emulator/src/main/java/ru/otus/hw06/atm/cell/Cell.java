package ru.otus.hw06.atm.cell;

import ru.otus.hw06.banknote.Banknote;
import ru.otus.hw06.banknote.Bundle;

// Ячейка хранит в себе некоторое количество банкнот одного типа
public class Cell {
    private Bundle bundle;
    private Banknote banknote;

    public Cell(Banknote banknote, int count) {
        this.banknote = banknote;
        this.bundle = new Bundle();
        this.bundle.add(banknote, count);
    }

    public void insertBills(int count) {
        bundle.add(banknote, count);
    }

    public void withdrawBills(int count) {
        bundle.withdraw(banknote, count);
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return bundle.getCountByBanknote(banknote);
    }

    public int getSum() {
        return bundle.sum();
    }
}
