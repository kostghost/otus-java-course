package ru.otus.hw07.department.holder;

import ru.otus.hw06.atm.Atm;

public class AtmState {

    Atm state;

    public AtmState(Atm atm) {
        state = (Atm) atm.copy();
    }

    public Atm getState() {
        return state;
    }
}
