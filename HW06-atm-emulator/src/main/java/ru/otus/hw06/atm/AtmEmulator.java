package ru.otus.hw06.atm;


import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import ru.otus.hw06.atm.cell.Cell;
import ru.otus.hw06.currency.Bundle;
import ru.otus.hw06.currency.Currency;

public class AtmEmulator {
    // sorted DESC
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

    // TODO ух, может все-таки сделать какую-то обертку над cells?
    public void addMoney(Bundle bundle) {
        for (Currency currency : bundle.getCurrencies()) {
            if (cells.stream().anyMatch(x -> x.getCurrency().equals(currency))) {
                cells.stream()
                        .filter(x -> x.getCurrency().equals(currency))
                        .forEach(x -> x.insertBills(bundle.getCountByCurrency(currency)));
            } else {
                cells.add(new Cell(currency, bundle.getCountByCurrency(currency)));
            }
        }
    }

    public void withdrawMoney(int value) {
        Bundle bundle = getBundleToWithdraw(value);

        // тут уже упали в случае, если не смогли собрать bundle

        for (Cell cell : cells) {
            cell.withdrawBills(bundle.getCountByCurrency(cell.getCurrency()));
        }
    }

    private Bundle getBundleToWithdraw(int value) {
        Bundle bundle = new Bundle();

        for (Cell cell : cells) {
            int countOfBillsWeWant = (value - bundle.sum()) / cell.getCurrency().getValue();
            int countOfBillsWeCanWithdraw = Math.min(countOfBillsWeWant, cell.getCount());

            bundle.add(cell.getCurrency(), countOfBillsWeCanWithdraw);

            if (bundle.sum() == value) {
                return bundle;
            }
        }

        throw new NoMoneyException();
    }
}
