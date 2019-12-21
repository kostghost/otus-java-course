package ru.otus.hw07.department.command;

import ru.otus.hw07.department.AtmDepartmentClient;

public abstract class AtmDepartmentCommand {

    protected AtmDepartmentClient client;

    public AtmDepartmentCommand(AtmDepartmentClient client) {
        this.client = client;
    }

    public abstract void rollback();

    public abstract boolean execute();
}
