package ru.otus.hw07.department.gui;

public class AtmGuiImpl implements AtmGui {

    private String lastMessage = "";

    @Override
    public void printMessage(String message) {
        this.lastMessage = message;
    }

    @Override
    public String getLastMessage() {
        return lastMessage;
    }
}
