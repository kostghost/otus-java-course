package ru.otus.hw06.currency;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ru.otus.hw06.atm.NoMoneyException;


// Представляет из себя пачку денег
public class Bundle {
    private Map<Currency, Integer> bundle;

    public Bundle() {
        bundle = new HashMap<>();
    }

    public Bundle add(Currency currency, int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        if (bundle.containsKey(currency)) {
            var oldCount = bundle.get(currency);
            bundle.replace(currency, oldCount + count);
            return this;
        }
        bundle.put(currency, count);
        return this;
    }

    public void withdraw(Currency currency, int count) {
        var countExists = bundle.getOrDefault(currency, 0);
        if (countExists < count) {
            throw new NoMoneyException();
        }
        bundle.replace(currency, countExists - count);
    }


//    private boolean canWithdrawBills(Currency currency, int count) {
//        return new ExecutionControl.NotImplementedException();
//    }


    public Integer getCountByCurrency(Currency currency) {
        return bundle.getOrDefault(currency, 0);
    }

    public Set<Currency> getCurrencies() {
        return bundle.keySet();
    }

    public int sum() {
        int sum = 0;
        for (var currency : bundle.keySet()) {
            sum += currency.getValue() * bundle.get(currency);
        }
        return sum;
    }
}
