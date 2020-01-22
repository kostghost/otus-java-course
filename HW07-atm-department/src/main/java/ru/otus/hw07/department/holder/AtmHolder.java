package ru.otus.hw07.department.holder;

import ru.otus.hw06.atm.Atm;

public interface AtmHolder {
    void addAtm(Atm atm);

    Iterable<Atm> getAtms();

    Atm getAtm(String id);

    void resetAll();
}
