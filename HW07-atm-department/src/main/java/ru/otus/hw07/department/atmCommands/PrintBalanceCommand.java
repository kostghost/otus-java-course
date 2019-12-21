package ru.otus.hw07.department.atmCommands;

import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class PrintBalanceCommand extends AtmDepartmentCommand {

    public PrintBalanceCommand(AtmDepartmentClient client) {
        super(client);
    }

    @Override
    public void rollback() {
        // nothing to rollback
    }

    @Override
    public boolean execute() {
        client.getAtmGui().printMessage(getBalance());
        return false;
    }


    private String getBalance() {
        return "BALANCE NOT IMPLEMENTED";
    }
}
