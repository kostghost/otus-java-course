package ru.otus.hw06.banknote.rouble;

import ru.otus.hw06.banknote.Banknote;
import ru.otus.hw06.banknote.Currency;

// Возможно, это слишком переигрывание в ООП и можно было ограничиться просто одним классом Rouble,
// не делая релизации абстрактного класса
// Но так, как мне кажется, подчеркивается, что деньги - что-то внешнее и неизменяемое
// в ходе работы банкомата.
public abstract class Rouble extends Banknote {

    Rouble(int value) {
        super(value);
    }

    @Override
    public int getValue() {
        return super.getValue();
    }

    @Override
    public Currency getCurrency() {
        return Currency.ROUBLE;
    }
}
