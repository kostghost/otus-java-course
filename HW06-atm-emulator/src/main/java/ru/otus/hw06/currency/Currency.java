package ru.otus.hw06.currency;


public interface Currency extends Comparable<Currency> {
    // Счтаем, что банкомат работает только с целыми числами
    int getValue();

    String getName();

    String getShortName();

//    int compareTo(Rouble o);
//
//    boolean equals(Object o);
//
//    int hashCode();
}
