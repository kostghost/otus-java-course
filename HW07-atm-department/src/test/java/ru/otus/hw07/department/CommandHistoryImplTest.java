package ru.otus.hw07.department;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.otus.hw07.department.command.AtmDepartmentCommand;
import ru.otus.hw07.department.command.CommandHistoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandHistoryImplTest {
    @Mock
    private AtmDepartmentCommand testCommandOne;
    @Mock
    private AtmDepartmentCommand testCommandTwo;
    @Mock
    private AtmDepartmentClient client;

    private CommandHistoryImpl commandHistory;

    @BeforeEach
    void before() {
        client = Mockito.mock(AtmDepartmentClient.class);
        commandHistory = new CommandHistoryImpl();
        testCommandOne = Mockito.mock(AtmDepartmentCommand.class);
        testCommandTwo = Mockito.mock(AtmDepartmentCommand.class);
    }

    @Test
    void testWork() {
        commandHistory.push(testCommandOne);
        commandHistory.push(testCommandTwo);

        assertEquals(testCommandTwo, commandHistory.pop());
        assertEquals(testCommandOne, commandHistory.pop());
    }

    @Test
    void testDontWork() {
        commandHistory.push(testCommandOne);
        commandHistory.pop();

        assertThrows(EmptyStackException.class, () -> commandHistory.pop());
    }

}