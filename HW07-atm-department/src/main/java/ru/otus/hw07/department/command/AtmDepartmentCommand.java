package ru.otus.hw07.department.command;

import ru.otus.hw07.department.AtmDepartmentClient;

public abstract class AtmDepartmentCommand {

    protected AtmDepartmentClient client;

    public AtmDepartmentCommand(AtmDepartmentClient client) {
        this.client = client;
    }

    // можно было бы добавить еще метод rollback и откатывать операции покомандно.
    // Непонятно, как это нужно реализовать с точки зрения бизнеса
    // т.к. умеем откатывать состояния банкоматов, чего в жизни быть не может.

    public abstract void execute();

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }
}
