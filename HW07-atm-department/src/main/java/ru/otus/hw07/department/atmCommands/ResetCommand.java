package ru.otus.hw07.department.atmCommands;

import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.command.AtmDepartmentCommand;

public class ResetCommand extends AtmDepartmentCommand {

    /**
     * Возвращает все банкоматы в состояние, в котором они были добавлены.
     *
     * @param client
     */
    public ResetCommand(AtmDepartmentClient client) {
        super(client);
    }

    @Override
    public void execute() {
        client.getHistory().clear();
        client.getAtmHolder().resetAll();
    }
}
