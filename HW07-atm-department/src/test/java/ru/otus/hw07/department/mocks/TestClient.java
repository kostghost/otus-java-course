package ru.otus.hw07.department.mocks;

import ru.otus.hw07.department.AtmDepartmentClient;
import ru.otus.hw07.department.AtmHolder;
import ru.otus.hw07.department.command.AtmDepartmentCommand;
import ru.otus.hw07.department.gui.AtmGui;

public class TestClient implements AtmDepartmentClient {

    @Override
    public AtmHolder getAtmHolder() {
        return null;
    }

    @Override
    public AtmGui getAtmGui() {
        return null;
    }

    @Override
    public void undo() {

    }

    @Override
    public void executeCommand(AtmDepartmentCommand command) {

    }
}
