package ru.otus.hw07.department;

import ru.otus.hw06.atm.Atm;
import ru.otus.hw06.banknote.Bundle;

/**
 * Использовать AtmDepartment нужно именно через этот интерфейс
 */
public interface AtmDepartmentApi {

    /**
     * Может создавать копии atm, поэтому действия, сделанные с atm напрямую
     * могут не повлиять на Department и наоборот
     *
     * @param atm
     */
    void addAtm(Atm atm);

    int getBalance();

    void resetAllAtm();

    void addMoneyToAtm(String atmId, Bundle bundle);
}
