package ru.otus.hw07.department;

import java.util.ArrayList;
import java.util.List;

import ru.otus.hw06.atm.Atm;

class AtmHolderImpl implements AtmHolder {

    private List<Atm> atmList = new ArrayList<>();

    @Override
    public void addAtm(Atm atm) {
        atmList.add(atm);
    }

    @Override
    public Iterable<Atm> getAtms() {
        return atmList;
    }
}
