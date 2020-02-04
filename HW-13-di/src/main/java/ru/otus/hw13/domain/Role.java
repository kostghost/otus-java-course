package ru.otus.hw13.domain;

public enum Role {
    ADMIN(0),
    USER(1);

    private int id;

    Role(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
