package ru.otus.hw06.banknote;

public interface Banknote extends Comparable<Banknote> {

    int getValue();

    Currency getCurrency();
}
