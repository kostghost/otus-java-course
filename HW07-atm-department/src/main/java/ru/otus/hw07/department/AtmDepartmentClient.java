package ru.otus.hw07.department;

import ru.otus.hw07.department.command.AtmDepartmentCommand;
import ru.otus.hw07.department.command.CommandHistory;
import ru.otus.hw07.department.gui.AtmGui;
import ru.otus.hw07.department.holder.AtmHolder;

public interface AtmDepartmentClient {

    AtmHolder getAtmHolder();

    AtmGui getAtmGui();

    CommandHistory getHistory();

    void executeCommand(AtmDepartmentCommand command);
}
