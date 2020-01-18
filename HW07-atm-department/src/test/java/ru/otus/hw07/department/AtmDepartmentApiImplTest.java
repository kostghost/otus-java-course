package ru.otus.hw07.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.atm.Atm;
import ru.otus.hw06.atm.AtmEmulator;
import ru.otus.hw06.banknote.Bundle;
import ru.otus.hw06.banknote.BundleImpl;
import ru.otus.hw06.banknote.rouble.FiveHundredRoubles;
import ru.otus.hw06.banknote.rouble.OneHundredRoubles;
import ru.otus.hw06.banknote.rouble.OneRouble;
import ru.otus.hw06.banknote.rouble.TenRoubles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AtmDepartmentApiImplTest {

    private AtmDepartmentApi department;

    private Atm atmOne;
    private Atm atmTwo;
    private Atm atmThree;

    @BeforeEach
    void before() {
        department = new AtmDepartmentApiImpl();

        Bundle bundleOne = new BundleImpl()
                .add(new FiveHundredRoubles(), 5)
                .add(new TenRoubles(), 5)
                .add(new OneHundredRoubles(), 3);

        Bundle bundleTwo = new BundleImpl()
                .add(new FiveHundredRoubles(), 1)
                .add(new TenRoubles(), 1);

        Bundle bundleThree = new BundleImpl()
                .add(new OneRouble(), 3)
                .add(new OneHundredRoubles(), 1);

        atmOne = new AtmEmulator(bundleOne, "atmOne");
        atmTwo = new AtmEmulator(bundleTwo, "atmTwo");
        atmThree = new AtmEmulator(bundleThree, "atmThree");
    }

    @Test
    void addAtmTest() {
        department.addAtm(atmOne);
    }

    @Test
    void balanceTest() {
        department.addAtm(atmOne);
        department.addAtm(atmTwo);

        int expected = atmOne.getBalance() + atmTwo.getBalance();
        assertEquals(expected, department.getBalance());

        department.addAtm(atmThree);
        expected += atmThree.getBalance();

        assertEquals(expected, department.getBalance());
    }

    @Test
    void initBalanceTest() {
        department.addAtm(atmOne);
        department.addAtm(atmTwo);

        int expected = atmOne.getBalance() + atmTwo.getBalance();
        assertEquals(expected, department.getBalance());
    }

    @Test
    void balanceAfterWorkWithAtmTest() {
        department.addAtm(atmOne);
        department.addAtm(atmTwo);

        var balance = department.getBalance();

        var bundleToAdd = new BundleImpl().add(new OneHundredRoubles(), 1);

        department.addMoneyToAtm(
                atmOne.getId(), bundleToAdd
        );

        int expected = balance + bundleToAdd.getSum();

        assertEquals(expected, department.getBalance());
    }


    @Test
    void resetAllTest() {
        department.addAtm(atmOne);
        department.addAtm(atmTwo);

        var balanceBefore = department.getBalance();

        // добавили в один банкомат денег
        department.addMoneyToAtm(atmOne.getId(),
                new BundleImpl().add(new OneHundredRoubles(), 1));
        assertNotEquals(balanceBefore, department.getBalance());

        department.resetAllAtm();

        assertEquals(balanceBefore, department.getBalance());
    }
}