package ru.otus.hw07.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.otus.hw06.atm.Atm;
import ru.otus.hw07.department.holder.AtmHolderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtmHolderImplTest {
    @Mock
    private Atm atm;

    @Mock
    private Atm atmTwo;

    private AtmHolderImpl atmHolder;

    @BeforeEach
    void before() {
        atm = Mockito.mock(Atm.class);
        Mockito.when(atm.getId()).thenReturn("one");

        atmTwo = Mockito.mock(Atm.class);
        Mockito.when(atmTwo.getId()).thenReturn("two");

        atmHolder = new AtmHolderImpl();
    }

    @Test
    void testWork() {
        atmHolder.addAtm(atm);
        atmHolder.addAtm(atmTwo);

        Iterable<Atm> atms = atmHolder.getAtms();
        var iterator = atms.iterator();

        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testHasNoNext() {
        Iterable<Atm> atms = atmHolder.getAtms();

        assertFalse(atms.iterator().hasNext());
    }

    @Test
    void testGetAtmById() {
        atmHolder.addAtm(atm);
        atmHolder.addAtm(atmTwo);

        assertEquals("one", atmHolder.getAtm("one").getId());
        assertEquals("two", atmHolder.getAtm("two").getId());
    }
}