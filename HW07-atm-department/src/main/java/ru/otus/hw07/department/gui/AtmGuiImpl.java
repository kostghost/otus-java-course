package ru.otus.hw07.department.gui;

public class AtmGuiImpl implements AtmGui {

    private String lastMessage = "";
    private int lastAmount;

    @Override
    public void printMessage(String message) {
        this.lastMessage = message;
    }

    @Override
    public void printAmount(int amount) {
        this.lastAmount = amount;
    }

    @Override
    public String getLastMessage() {
        return lastMessage;
    }

    @Override
    public int getLastAmount() {
        return lastAmount;
    }

}
