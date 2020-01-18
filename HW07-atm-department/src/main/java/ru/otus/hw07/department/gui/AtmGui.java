package ru.otus.hw07.department.gui;

public interface AtmGui {
    void printMessage(String message);

    void printAmount(int amount);

    String getLastMessage();

    int getLastAmount();
}
