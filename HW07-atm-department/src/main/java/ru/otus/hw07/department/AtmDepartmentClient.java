package ru.otus.hw07.department;

import ru.otus.hw07.department.command.AtmDepartmentCommand;
import ru.otus.hw07.department.gui.AtmGui;

public interface AtmDepartmentClient {

    AtmHolder getAtmHolder();

    AtmGui getAtmGui();

    void undo();

    void executeCommand(AtmDepartmentCommand command);
}
