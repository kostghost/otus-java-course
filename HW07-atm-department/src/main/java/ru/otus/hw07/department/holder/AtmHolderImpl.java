package ru.otus.hw07.department.holder;

import java.util.HashMap;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw07.department.exceptions.AtmAlreadyAddedException;
import ru.otus.hw07.department.exceptions.CantRestoreAtmStateException;
import ru.otus.hw07.department.exceptions.NoAtmException;
import ru.otus.hw07.department.exceptions.NoSuchStateException;

public class AtmHolderImpl implements AtmHolder {

    private HashMap<String, AtmOriginator> atmHistoryMap = new HashMap<>();
    private HashMap<String, Atm> atmMap = new HashMap<>();

    @Override
    public void addAtm(Atm atm) throws AtmAlreadyAddedException {
        if (atmHistoryMap.containsKey(atm.getId())) {
            throw new AtmAlreadyAddedException();
        }

        var originator = new AtmOriginator();
        originator.createRecovery(atm);

        atmMap.put(atm.getId(), atm);
        atmHistoryMap.put(atm.getId(), originator);
    }

    public Atm getAtm(String id) {
        if (!atmHistoryMap.containsKey(id)) {
            throw new NoAtmException();
        }
        return atmMap.get(id);
    }

    @Override
    public Iterable<Atm> getAtms() {
        return atmMap.values();
    }

    @Override
    public void resetAll() {
        for (AtmOriginator originator : atmHistoryMap.values()) {
            try {
                var restoredAtm = restoreFirstState(originator);
                atmMap.replace(restoredAtm.getId(), restoredAtm);

                originator.createRecovery(restoredAtm);
            } catch (NoSuchStateException ex) {
                throw new CantRestoreAtmStateException();
            }
        }
    }

    private Atm restoreFirstState(AtmOriginator originator) throws NoSuchStateException {
        Atm restoredAtm = originator.restore();
        while (true) {
            try {
                restoredAtm = originator.restore();
            } catch (NoSuchStateException ex) {
                return restoredAtm;
            }
        }
    }
}
