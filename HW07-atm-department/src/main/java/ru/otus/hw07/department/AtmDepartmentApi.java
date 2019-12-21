package ru.otus.hw07.department;

import ru.otus.hw06.atm.Atm;

// Использовать AtmDepartment нужно именно через этот интерфейс
public interface AtmDepartmentApi {
    void addAtm(Atm atm);

    String getBalance();

    void resetAllAtm();
}
