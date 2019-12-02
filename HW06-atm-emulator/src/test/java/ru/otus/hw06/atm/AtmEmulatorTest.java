package ru.otus.hw06.atm;

import org.junit.jupiter.api.Test;
import ru.otus.hw06.currency.Bundle;
import ru.otus.hw06.currency.rouble.FiveHundredRoubles;
import ru.otus.hw06.currency.rouble.OneHundredRoubles;
import ru.otus.hw06.currency.rouble.TenRoubles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmEmulatorTest {

    private AtmEmulator generateEmulator() {
        return new AtmEmulator(new Bundle()
                .add(new FiveHundredRoubles(), 5)
                .add(new TenRoubles(), 5)
                .add(new OneHundredRoubles(), 3));
    }

    @Test
    void balanceOrdinary() {
        AtmEmulator emulator = generateEmulator();
        assertEquals(2850, emulator.getBalance());
    }

    @Test
    void balanceZero() {
        AtmEmulator emulator = new AtmEmulator(new Bundle());

        assertEquals(0, emulator.getBalance());
    }

    @Test
    void printBalance() {
        AtmEmulator emulator = generateEmulator();
        printAllInfo(emulator);
    }

    @Test
    void withdrawMoney() {
        AtmEmulator emulator = generateEmulator();
        assertEquals(2850, emulator.getBalance());

        emulator.withdrawMoney(2710);
        assertEquals(140, emulator.getBalance());
    }

    @Test
    void cantWithdrawMoney() {
        AtmEmulator emulator = generateEmulator();
        assertEquals(2850, emulator.getBalance());

        assertThrows(NoMoneyException.class, () -> emulator.withdrawMoney(2790));
    }

    @Test
    void fullTest() {
        AtmEmulator emulator = generateEmulator();

        printAllInfo(emulator);

        emulator.addMoney(new Bundle().add(new OneHundredRoubles(), 4));

        printAllInfo(emulator);
    }

    private void printAllInfo(AtmEmulator emulator) {
        System.out.println(emulator.getDetailedBalance());
        System.out.println(emulator.getBalance());
        System.out.println();
    }

}