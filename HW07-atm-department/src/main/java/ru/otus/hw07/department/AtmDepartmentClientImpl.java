package ru.otus.hw07.department;

import ru.otus.hw07.department.command.AtmDepartmentCommand;
import ru.otus.hw07.department.command.CommandHistory;
import ru.otus.hw07.department.gui.AtmGui;
import ru.otus.hw07.department.holder.AtmHolder;

public class AtmDepartmentClientImpl implements AtmDepartmentClient {

    private AtmHolder atmHolder;
    private AtmGui atmGui;
    private CommandHistory history;

    public AtmDepartmentClientImpl(AtmHolder atmHolder,
                                   AtmGui atmGui,
                                   CommandHistory history) {
        this.atmHolder = atmHolder;
        this.atmGui = atmGui;
        this.history = history;
    }

    @Override
    public void executeCommand(AtmDepartmentCommand command) {
        command.execute();
        history.push(command);
    }

    @Override
    public AtmHolder getAtmHolder() {
        return atmHolder;
    }

    @Override
    public AtmGui getAtmGui() {
        return atmGui;
    }

    @Override
    public CommandHistory getHistory() {
        return history;
    }
}
