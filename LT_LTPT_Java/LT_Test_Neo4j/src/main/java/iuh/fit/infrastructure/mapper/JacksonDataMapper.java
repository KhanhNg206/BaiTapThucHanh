package iuh.fit.infrastructure.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDataMapper<T> implements GenericDataMapper<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Object> toMap(T object) {
        return mapper.convertValue(object, new TypeReference<>() {});
    }

    @Override
    public T fromMap(Map<String, Object> map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }
}