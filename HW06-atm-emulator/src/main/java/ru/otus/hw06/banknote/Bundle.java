package ru.otus.hw06.banknote;

import java.util.Set;

public interface Bundle {

    Bundle add(Banknote banknote, int count);

    Integer getCountByBanknote(Banknote banknote);

    Set<Banknote> getBanknotes();

    int getSum();
}
