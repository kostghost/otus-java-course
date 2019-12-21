package ru.otus.hw07.department.mocks;

import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class TestCommand extends AtmDepartmentCommand {

    public TestCommand(AtmDepartmentClient client) {
        super(client);
    }

    @Override
    public void rollback() {

    }

    @Override
    public boolean execute() {
        return false;
    }
}
