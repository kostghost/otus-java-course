package ru.otus.hw07.department;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw07.department.command.CommandHistoryImpl;
import ru.otus.hw07.department.mocks.TestClient;
import ru.otus.hw07.department.mocks.TestCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandHistoryImplTest {
    private TestCommand testCommandOne;
    private TestCommand testCommandTwo;
    private TestClient client;
    private CommandHistoryImpl commandHistory;

    @BeforeEach
    void before() {
        client = new TestClient();
        commandHistory = new CommandHistoryImpl();
        testCommandOne = new TestCommand(client);
        testCommandTwo = new TestCommand(client);
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