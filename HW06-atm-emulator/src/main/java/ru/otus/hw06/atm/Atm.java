package ru.otus.hw06.atm;

import ru.otus.hw06.banknote.Bundle;
import ru.otus.hw06.banknote.Currency;

public interface Atm {
    int getBalance();

    String getDetailedBalance();

    void addMoney(Bundle bundle);

    void withdrawMoney(int value, Currency currency);
}
