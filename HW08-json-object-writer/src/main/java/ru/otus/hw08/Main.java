package ru.otus.hw08;

import javax.json.Json;

public class Main {
    public static void main(String[] args) {
        var jsonObject = Json.createObjectBuilder()
                .add("djigurda", 1)
                .add("pyen koloda", "asdas!")
                .build();

        System.out.println(jsonObject);
    }
}
