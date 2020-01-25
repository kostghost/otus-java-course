package ru.otus.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.HasIdJdbcTemplate;

public class ObjectMapperImpl implements ObjectMapper {

    private static final Logger logger = LoggerFactory.getLogger(HasIdJdbcTemplate.class);

    @Override
    public LinkedHashMap<String, Object> getObjectFieldMap(Object object) {
        var result = new LinkedHashMap<String, Object>();

        getFields(object.getClass()).forEach(
                field -> getValue(field, object).map(
                        value -> result.put(field.getName(), value)
                )
        );

        return result;
    }

    @Override
    public List<String> getFieldNames(Class<?> clazz) {
        return getFields(clazz).stream()
                .map(Field::getName)
                .collect(Collectors.toList());
    }


    @Override
    public List<String> getFieldNamesWithAnnotaion(Class<?> clazz, Class<?> annotationClass) {
        var fields = getFields(clazz);

        return fields.stream()
                .filter(field -> isFieldHasAnnotation(field, annotationClass))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String getObjectClassName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    private boolean isFieldHasAnnotation(Field field, Class<?> annotationClass) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }

    // Можно кэшировать результат работы этого метода
    private List<Field> getFields(Class<?> clazz) {

        return Arrays
                .stream(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .collect(Collectors.toList());
    }

    private Optional<Object> getValue(Field field, Object object) {
        boolean isAccessible = field.canAccess(object);
        if (!isAccessible) {
            field.setAccessible(true);
        }

        Optional<Object> result = Optional.empty();

        field.setAccessible(true);

        try {
            result = Optional.ofNullable(field.get(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (!isAccessible) {
            field.setAccessible(false);
        }

        return result;
    }

    @Override
    public <T> T generateObject(Class<T> clazz, Map<String, Object> fields) {
        T result = null;

        // Имеем проблемы с разным кейсом букв. Поэтому - хак
        // Можно его, конечно, обойти
        Map<String, Object> nonCaseSensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        nonCaseSensitiveMap.putAll(fields);

        try {
            // deprecated, использует конструктор по умолчанию, которого может не быть, но
            // иначе придется возиться с getConstructors много и долго :)
            result = clazz.newInstance();

            var declaredFields = clazz.getDeclaredFields();


            for (var field : declaredFields) {
                var name = field.getName().toLowerCase();
                var value = nonCaseSensitiveMap.get(name);

                boolean accessible = field.isAccessible();
                field.setAccessible(true);

                field.set(result, value);

                field.setAccessible(accessible);

            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }


}
