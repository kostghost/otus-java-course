package ru.otus.hw06.banknote;

public enum Currency {
    NOT_SPECIFIED("ед."),
    ROUBLE("руб.");

    String shortName;

    Currency(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
