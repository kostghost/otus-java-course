package ru.otus.hw07.department.mocks;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw06.banknote.Bundle;
import ru.otus.hw06.banknote.Currency;

public class TestAtm implements Atm {
    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public String getDetailedBalance() {
        return null;
    }

    @Override
    public void addMoney(Bundle bundle) {

    }

    @Override
    public void withdrawMoney(int value, Currency currency) {

    }
}
