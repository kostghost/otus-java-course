package ru.otus.hw06.banknote;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Представляет из себя пачку денег
public class BundleImpl implements Bundle {
    private Map<Banknote, Integer> bundle;

    public BundleImpl() {
        bundle = new HashMap<>();
    }

    public Bundle add(Banknote banknote, int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        if (bundle.containsKey(banknote)) {
            var oldCount = bundle.get(banknote);
            bundle.replace(banknote, oldCount + count);
            return this;
        }
        bundle.put(banknote, count);
        return this;
    }

    public Integer getCountByBanknote(Banknote banknote) {
        return bundle.getOrDefault(banknote, 0);
    }

    public Set<Banknote> getBanknotes() {
        return bundle.keySet();
    }

    public int getSum() {
        int sum = 0;
        for (var currency : bundle.keySet()) {
            sum += currency.getValue() * bundle.get(currency);
        }
        return sum;
    }
}
