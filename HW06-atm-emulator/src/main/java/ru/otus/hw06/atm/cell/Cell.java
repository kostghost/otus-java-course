package ru.otus.hw06.atm.cell;

import ru.otus.hw06.banknote.Banknote;

public interface Cell {

    void insertBills(int count);

    void withdrawBills(int count);

    Banknote getBanknote();

    int getCount();

    int getSum();
}
