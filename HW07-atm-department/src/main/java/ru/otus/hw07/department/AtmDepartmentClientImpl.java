package ru.otus.hw07.department;

import ru.otus.hw07.department.command.AtmDepartmentCommand;
import ru.otus.hw07.department.command.CommandHistory;
import ru.otus.hw07.department.gui.AtmGui;

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

    public void executeCommand(AtmDepartmentCommand command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    public void undo() {
        var command = history.pop();

        if (command != null) {
            command.rollback();
        }
    }

    public AtmHolder getAtmHolder() {
        return atmHolder;
    }

    public AtmGui getAtmGui() {
        return atmGui;
    }

}
