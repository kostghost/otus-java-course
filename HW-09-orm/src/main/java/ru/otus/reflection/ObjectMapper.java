package ru.otus.reflection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ObjectMapper {

    // Используется LinkedHashMap чтобы хранить порядок.
    LinkedHashMap<String, Object> getObjectFieldMap(Object object);

    List<String> getFieldNames(Class<?> clazz);

    List<String> getFieldNamesWithAnnotaion(Object object, Class<?> annotationClass);

    String getObjectClassName(Class<?> clazz);

    <T> T generateObject(Class<T> clazz, Map<String, Object> fields);
}
