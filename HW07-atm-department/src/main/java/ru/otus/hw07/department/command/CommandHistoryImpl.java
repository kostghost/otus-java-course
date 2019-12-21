package ru.otus.hw07.department.command;

import java.util.Stack;

public class CommandHistoryImpl implements CommandHistory {

    private Stack<AtmDepartmentCommand> commands;

    public CommandHistoryImpl() {
        this.commands = new Stack<>();
    }

    @Override
    public void push(AtmDepartmentCommand command) {
        commands.push(command);
    }

    @Override
    public AtmDepartmentCommand pop() {
        return commands.pop();
    }
}
