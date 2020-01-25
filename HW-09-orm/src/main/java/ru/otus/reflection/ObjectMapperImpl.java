package ru.otus.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.HasIdJdbcTeamplate;

public class ObjectMapperImpl implements ObjectMapper {

    private static final Logger logger = LoggerFactory.getLogger(HasIdJdbcTeamplate.class);

    @Override
    public LinkedHashMap<String, Object> getObjectFieldMap(Object object) {
        var result = new LinkedHashMap<String, Object>();

        getFields(object).forEach(
                field -> getValue(field, object).map(
                        value -> result.put(field.getName(), value)
                )
        );

        return result;
    }

    @Override
    public List<String> getFieldNamesWithAnnotaion(Object object, Class<?> annotationClass) {
        var fields = getFields(object);

        return fields.stream()
                .filter(field -> isFieldHasAnnotation(field, annotationClass))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String getObjectClassName(Object object) {
        return object.getClass().getSimpleName();
    }

    private boolean isFieldHasAnnotation(Field field, Class<?> annotationClass) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }

    // Можно кэшировать результат работы этого метода
    private List<Field> getFields(Object object) {
        var clazz = object.getClass();

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
        try {
            // deprecated, использует конструктор по умолчанию, которого может не быть, но
            // иначе придется возиться с getConstructors много и долго :)
            result = clazz.newInstance();

            for (var key : fields.keySet()) {
                var declaredField = clazz.getDeclaredField(key);

                boolean accessible = declaredField.isAccessible();
                declaredField.setAccessible(true);

                declaredField.set(result, fields.get(key));

                declaredField.setAccessible(accessible);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

}
