package ru.otus.hw07.department.holder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw07.department.exceptions.NoSuchStateException;

public class AtmOriginator {

    private final Deque<AtmState> stack = new ArrayDeque<>();

    public void createRecovery(Atm state) {
        stack.push(new AtmState(state));
    }

    public Atm restore() throws NoSuchStateException {
        try {
            return stack.pop().getState();
        } catch (NoSuchElementException | NullPointerException ex) {
            throw new NoSuchStateException();
        }
    }
}
