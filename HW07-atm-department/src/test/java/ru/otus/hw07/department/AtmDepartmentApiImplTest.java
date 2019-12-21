package ru.otus.hw07.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.atm.Atm;
import ru.otus.hw06.atm.AtmEmulator;
import ru.otus.hw06.banknote.Bundle;
import ru.otus.hw06.banknote.BundleImpl;
import ru.otus.hw06.banknote.rouble.FiveHundredRoubles;
import ru.otus.hw06.banknote.rouble.OneHundredRoubles;
import ru.otus.hw06.banknote.rouble.TenRoubles;

class AtmDepartmentApiImplTest {

    private Bundle bundleOne;
    private Bundle bundleTwo;

    private Atm atmOne;
    private Atm atmTwo;

    @BeforeEach
    void before() {
        bundleOne = new BundleImpl()
                .add(new FiveHundredRoubles(), 5)
                .add(new TenRoubles(), 5)
                .add(new OneHundredRoubles(), 3);

        bundleTwo = new BundleImpl()
                .add(new FiveHundredRoubles(), 1)
                .add(new TenRoubles(), 1);

        atmOne = new AtmEmulator(bundleOne);
        atmTwo = new AtmEmulator(bundleTwo);
    }


    @Test
    void hyperTest() {
        var department = new AtmDepartmentApiImpl();

        department.addAtm(atmOne);
        department.addAtm(atmTwo);

        var asd = department.getBalance();

        department.resetAllAtm();
    }
}