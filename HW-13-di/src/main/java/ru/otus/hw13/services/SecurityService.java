package ru.otus.hw13.services;

public interface SecurityService {

    boolean isAdmin(String login, String password);

    boolean checkPassword(String login, String password);
}
