package ru.otus.hw07.department;

import ru.otus.hw06.atm.Atm;

public interface AtmHolder {
    void addAtm(Atm atm);

    Iterable<Atm> getAtms();
}
