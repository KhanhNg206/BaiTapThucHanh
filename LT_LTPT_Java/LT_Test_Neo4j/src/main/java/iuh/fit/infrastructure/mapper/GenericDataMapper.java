package iuh.fit.infrastructure.mapper;

import java.util.Map;

public interface GenericDataMapper<T> {

    Map<String, Object> toMap(T object);

    T fromMap(Map<String, Object> map, Class<T> clazz);
}