package ru.otus.hw06.atm;


import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import ru.otus.hw06.atm.cell.Cell;
import ru.otus.hw06.currency.Bundle;
import ru.otus.hw06.currency.Currency;

public class AtmEmulator {
    private SortedSet<Cell> cells;

    public AtmEmulator(Bundle bundle) {
        cells = new TreeSet<>();
        addMoney(bundle);
    }

    public int getBalance() {
        return cells.stream().map(Cell::getSum).reduce(0, Integer::sum);
    }

    public String getDetailedBalance() {
        return cells.stream()
                .map(x -> x.getCurrency().getValue() +
                        x.getCurrency().getShortName() + " " +
                        ": " + x.getCount() + "шт. " +
                        "Сумма:" + x.getSum()
                )
                .collect(Collectors.joining("\n"));
    }

    public void addMoney(Bundle bundle) {
        for (Currency currency : bundle.getCurrencies()) {
            cells.add(new Cell(currency, bundle.getCountByCurrency(currency)));
        }
    }

    public void withdrawMoney(int value) {
        // @todo
        //        Bundle bundleToWithdraw = new Bundle();
//
//        for (var cell : cells) {
//            if (bundleToWithdraw.sum() == cell.getCurrency().getValue()){
//                return;
//            }
//
//        }
    }


}
