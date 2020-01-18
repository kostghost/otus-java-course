package ru.otus.hw07.department.atmCommands;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class PrintBalanceCommand extends AtmDepartmentCommand {

    public PrintBalanceCommand(AtmDepartmentClient client) {
        super(client);
    }

    @Override
    public void execute() {
        client.getAtmGui().printAmount(getBalance(client));
    }


    private int getBalance(AtmDepartmentClient client) {
        Iterable<Atm> atms = client.getAtmHolder().getAtms();

        int balance = 0;
        for (var atm : atms) {
            balance += atm.getBalance();
        }

        return balance;
    }
}
