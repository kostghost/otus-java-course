package ru.otus.hw06.atm;

import org.junit.jupiter.api.Test;
import ru.otus.hw06.currency.Bundle;
import ru.otus.hw06.currency.rouble.FiveHundredRoubles;
import ru.otus.hw06.currency.rouble.OneHundredRoubles;
import ru.otus.hw06.currency.rouble.TenRoubles;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtmEmulatorTest {

    private AtmEmulator generateEmulator() {
        return new AtmEmulator(new Bundle()
                .add(new FiveHundredRoubles(), 5)
                .add(new TenRoubles(), 10)
                .add(new OneHundredRoubles(), 3));
    }

    @Test
    void balanceOrdinary() {
        AtmEmulator emulator = generateEmulator();
        assertEquals(2900, emulator.getBalance());
    }

    @Test
    void balanceZero() {
        AtmEmulator emulator = new AtmEmulator(new Bundle());

        assertEquals(0, emulator.getBalance());
    }

    @Test
    void printBalance() {
        AtmEmulator emulator = generateEmulator();
        System.out.println(emulator.getDetailedBalance());
    }
}