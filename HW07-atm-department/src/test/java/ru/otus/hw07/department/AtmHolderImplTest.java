package ru.otus.hw07.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.atm.Atm;
import ru.otus.hw07.department.mocks.TestAtm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtmHolderImplTest {
    private TestAtm atm;
    private TestAtm atmTwo;
    private AtmHolderImpl atmHolder;

    @BeforeEach
    void before() {
        atm = new TestAtm();
        atmTwo = new TestAtm();

        atmHolder = new AtmHolderImpl();
    }

    @Test
    void testWork() {
        atmHolder.addAtm(atm);
        atmHolder.addAtm(atm);

        Iterable<Atm> atms = atmHolder.getAtms();
        var iterator = atms.iterator();

        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testHasNotNext() {
        Iterable<Atm> atms = atmHolder.getAtms();

        assertFalse(atms.iterator().hasNext());
    }
}