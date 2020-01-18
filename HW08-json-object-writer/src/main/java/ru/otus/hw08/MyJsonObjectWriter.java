package ru.otus.hw08;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class MyJsonObjectWriter implements JsonObjectWriter {

    @Override
    public String toJson(Object object) {
        return object == null
                ? JsonValue.NULL.toString()
                : this.toJsonInternal(object);
    }

    private String toJsonInternal(Object object) {
        try {
            return getJsonValue(object).toString();
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("ЭКСЕПТИОН!, " + e.getMessage(), e);
        }
    }

    private JsonObjectBuilder goDeeper(Object object, Class<?> clazz) throws IllegalAccessException {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            boolean needToTurnOffAccessible = false;
            if (!field.canAccess(object)) {
                field.setAccessible(true);
                needToTurnOffAccessible = true;
            }

            Object fieldValue;
            if (Modifier.isStatic(field.getModifiers())) {
                fieldValue = object;
            } else {
                fieldValue = field.get(object);
            }

            builder.add(field.getName(), getJsonValue(fieldValue));
            if (needToTurnOffAccessible) {
                field.setAccessible(false);
            }
        }
        return builder;
    }

    private JsonValue getJsonValue(Object value) throws IllegalAccessException {
        if (value == null) {
            return JsonValue.NULL;
        }

        var type = value.getClass();

        // а также другие примитивные и не очень типы
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return Json.createValue((int) value);

        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Json.createValue((double) value);

        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return Json.createValue((float) value);

        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Json.createValue((long) value);

        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return Json.createValue((short) value);

        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return Json.createValue((byte) value);

        } else if (type.equals(Character.class) || type.equals(char.class)) {
            return Json.createValue(String.valueOf(value));

        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return (boolean) value ? JsonValue.TRUE : JsonValue.FALSE;

        } else if (type.equals(String.class)) {
            return Json.createValue((String) value);

            // Далее более сложные типы
        } else if (type.isArray() || value instanceof Collection) {
            return goDeeperToCollections(value).build();

        } else if (value instanceof Map) {
            return goDeeperToMap((Map) value).build();
        } else {
            // В остальных случаях считаем что это вложенный объект и его надо бы раскопать!
            return goDeeper(value, type).build();
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

        for (var key : map.keySet()) {
            builder.add(key.toString(), getJsonValue(map.get(key)));
        }

        return builder;
    }

    private JsonArrayBuilder goDeeperToCollections(Object iterable) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        var array = anyToArray(iterable);

        if (array == null) {
            return arrayBuilder;
        }

        for (var el : array) {
            arrayBuilder.add(getJsonValue(el));
        }

        return arrayBuilder;
    }
}