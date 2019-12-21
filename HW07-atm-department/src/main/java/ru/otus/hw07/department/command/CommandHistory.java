package ru.otus.hw07.department.command;

public interface CommandHistory {

    void push(AtmDepartmentCommand command);

    AtmDepartmentCommand pop();
}
