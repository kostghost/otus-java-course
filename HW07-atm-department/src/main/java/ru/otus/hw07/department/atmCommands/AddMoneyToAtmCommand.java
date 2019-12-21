package ru.otus.hw07.department.atmCommands;

import ru.otus.hw06.banknote.Bundle;
import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class AddMoneyToAtmCommand extends AtmDepartmentCommand {
    private final String atmId;
    private final Bundle bundle;

    public AddMoneyToAtmCommand(AtmDepartmentClient client,
                                String atmId,
                                Bundle bundle) {
        super(client);
        this.atmId = atmId;
        this.bundle = bundle;
    }

    @Override
    public void execute() {
        client.getAtmHolder().getAtm(atmId).addMoney(bundle);
    }
}
