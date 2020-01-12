package ru.otus.hw08;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

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
            boolean needToTurnOffAccessible = false;
            if (!field.canAccess(object)) {
                field.setAccessible(true);
                needToTurnOffAccessible = true;
            }

            Object fieldValue;

            fieldValue = field.get(object);
            Class<?> fieldType = field.getType();

            builder.add(field.getName(), getJsonValue(fieldType, fieldValue));
            if (needToTurnOffAccessible) {
                field.setAccessible(false);
            }
        }
        return builder;
    }

    private JsonValue getJsonValue(Class<?> type, Object value) throws IllegalAccessException {

        if (value == null) {
            return JsonValue.NULL;
        }

        // а также другие примитивные и не очень типы
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return Json.createValue((int) value);

        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Json.createValue((double) value);

        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Json.createValue((long) value);

        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return (boolean) value ? JsonValue.TRUE : JsonValue.FALSE;

        } else if (type.equals(String.class)) {
            return Json.createValue((String) value);

        } else if (type.isArray() || value instanceof Collection) {
            return goDeeperToCollections(value).build();

        } else if (value instanceof Map) {
            return goDeeperToMap((Map) value).build();
        } else {
            // В остальных случаях считаем что это вложенный обхект и его надо бы раскопать!
            return goDeeper(value).build();
        }
    }

    private Object[] anyToArray(Object iterable) {
        Object[] array = null;

        if (iterable.getClass().isArray()) {
            if (iterable instanceof int[]) {
                array = Arrays.stream((int[]) iterable).boxed().toArray();
            } else if (iterable instanceof double[]) {
                array = Arrays.stream((double[]) iterable).boxed().toArray();
            } else if (iterable instanceof long[]) {
                array = Arrays.stream((long[]) iterable).boxed().toArray();
            } // И другие
            else if (iterable instanceof Object[]) {
                array = (Object[]) iterable;
            }
        } else if (iterable instanceof Iterable) {
            array = ((Collection) iterable).toArray();
        }
        return array;
    }

    private JsonObjectBuilder goDeeperToMap(Map map) throws IllegalAccessException {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (map.values().size() == 0) {
            return builder;
        }

        // Можем словить проблемы, если в Map храним разнотипные данные
        // (например, храним под Object, но getClass даст точный тип).
        // Считаем, что так не умеем
        var elsClass = (Class<?>) getClassForObjectStream(map.values().stream()).orElse(null);

        for (var key : map.keySet()) {
            builder.add(key.toString(), getJsonValue(elsClass, map.get(key)));
        }

        return builder;
    }

    private JsonArrayBuilder goDeeperToCollections(Object iterable) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        //Складываю в list для упрощения, хотя можно было бы и пользоваться iterator'ами

        var array = anyToArray(iterable);

        if (array == null) {
            return arrayBuilder;
        }

        // Можем словить проблемы, если в массиве храним разнотипные данные
        // (например, храним под Object, но getClass даст точный тип).
        // Считаем, что так не умеем
        Class<?> elsClass = getClassForObjectStream(Arrays.stream(array)).orElse(null);

        for (var el : array) {
            arrayBuilder.add(getJsonValue(elsClass, el));
        }

        return arrayBuilder;
    }

    private Optional<Class<?>> getClassForObjectStream(Stream<Object> stream) {
        return stream.filter(Objects::nonNull).findAny().map(Object::getClass);
    }
}