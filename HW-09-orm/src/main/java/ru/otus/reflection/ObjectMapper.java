package ru.otus.reflection;

import java.util.List;
import java.util.Map;

public interface ObjectMapper {

    Map<String, Object> getObjectFieldMap(Object object);

    List<String> getFieldNamesWithAnnotaion(Object object, Class<?> annotationClass);

    String getObjectClassName(Object object);
}
