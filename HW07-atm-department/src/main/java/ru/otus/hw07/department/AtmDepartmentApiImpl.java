package ru.otus.hw07.department;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw06.banknote.Bundle;
import ru.otus.hw07.department.atmCommands.AddAtmCommand;
import ru.otus.hw07.department.atmCommands.AddMoneyToAtmCommand;
import ru.otus.hw07.department.atmCommands.PrintBalanceCommand;
import ru.otus.hw07.department.atmCommands.ResetCommand;
import ru.otus.hw07.department.command.CommandHistoryImpl;
import ru.otus.hw07.department.gui.AtmGuiImpl;
import ru.otus.hw07.department.holder.AtmHolderImpl;

public class AtmDepartmentApiImpl implements AtmDepartmentApi {

    private AtmDepartmentClient client;

    public AtmDepartmentApiImpl() {
        var atmHolder = new AtmHolderImpl();
        var commandHistory = new CommandHistoryImpl();
        var gui = new AtmGuiImpl();

        this.client = new AtmDepartmentClientImpl(atmHolder, gui, commandHistory);
    }

    @Override
    public void addAtm(Atm atm) {
        client.executeCommand(new AddAtmCommand(client, atm));
    }

    @Override
    public int getBalance() {
        client.executeCommand(new PrintBalanceCommand(client));
        return client.getAtmGui().getLastAmount();
    }

    @Override
    public void resetAllAtm() {
        client.executeCommand(new ResetCommand(client));
    }

    @Override
    public void addMoneyToAtm(String atmId, Bundle bundle) {
        client.executeCommand(new AddMoneyToAtmCommand(client, atmId, bundle));
    }

}
