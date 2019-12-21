package ru.otus.hw07.department.atmCommands;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class AddAtmCommand extends AtmDepartmentCommand {

    private final Atm atmToAdd;

    public AddAtmCommand(AtmDepartmentClient client, Atm atmToAdd) {
        super(client);
        this.atmToAdd = atmToAdd;
    }

    @Override
    public void rollback() {

    }

    @Override
    public boolean execute() {
        client.getAtmHolder().addAtm(atmToAdd);

        return true;
    }
}
