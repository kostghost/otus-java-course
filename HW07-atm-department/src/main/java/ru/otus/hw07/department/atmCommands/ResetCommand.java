package ru.otus.hw07.department.atmCommands;

import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class ResetCommand extends AtmDepartmentCommand {

    public ResetCommand(AtmDepartmentClient client) {
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
