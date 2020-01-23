package ru.otus.api.model;

import java.math.BigDecimal;
import java.util.Objects;

import ru.otus.api.annotations.Id;

public class Account implements HasIdModel {

    @Id
    private final long no;
    private final String type;
    private final BigDecimal rest;

    public Account(long no, String type, BigDecimal rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getRest() {
        return rest;
    }


    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return no == account.no &&
                Objects.equals(type, account.type) &&
                Objects.equals(rest, account.rest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, type, rest);
    }
}
