package ru.otus.hw08;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class MyJsonObjectWriter implements JsonObjectWriter {


    @Override
    public String toJson(Object object) {

        JsonObjectBuilder builder = Json.createObjectBuilder();

        try {
            builder.addAll(goDeeper(object));
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("ЭКСЕПТИОН!, " + e.getMessage(), e);
        }

        JsonObject json = builder.build();
        return json.toString();
    }

    private JsonObjectBuilder goDeeper(Object object) throws IllegalAccessException {
        if (object == null) {
            return null;
        }

        JsonObjectBuilder builder = Json.createObjectBuilder();

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.canAccess(object)) {
                field.setAccessible(true); //TODO попрравить обратно, чтобы не заиметь сайд-эффекты
            }

            Object fieldValue;

            fieldValue = field.get(object);
            var fieldType = field.getType();

            if (fieldValue == null) {
                builder.addNull(field.getName());
                continue;
            }
            // Возможно стоит заменить сравнение на (подумать как) instanceOf
            if (fieldType.isPrimitive()) {
                // а также другие примитивные и не очень типы
                if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
                    builder.add(field.getName(), (int) fieldValue);
                } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                    builder.add(field.getName(), (double) fieldValue);
                } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                    builder.add(field.getName(), (long) fieldValue);
                } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                    builder.add(field.getName(), (boolean) fieldValue);
                }
            } else if (fieldType.equals(String.class)) {
                builder.add(field.getName(), (String) fieldValue);
            } else if (fieldType.isArray()) {
                // todo nothing
            } else if (fieldValue instanceof Collection || fieldValue instanceof Map) {
                // Эти и другие виды коллекций
                if (fieldValue instanceof List) {
                    builder.add(field.getName(), goDeeperToList((List) fieldValue));
                } else if (fieldValue instanceof Map) {
                    // todo nothing
                }
            } else {
                // В остальных случаях считаем что это вложенный обхект и его надо бы раскопать!
                builder.add(field.getName(), goDeeper(fieldValue));
            }
        }
        return builder;
    }

    // todo Вот тут каша, надо выделить добавление
    private JsonArrayBuilder goDeeperToList(List list) throws IllegalAccessException {
        // Так то мы тоже должны проходить по всем этим элементам через goDeeper т.к. там могут быть сложные объекты

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(list);

//        for (var listEl : list) {
//            builder.add(goDeeper(listEl));
//        }

        return arrayBuilder;
    }
}