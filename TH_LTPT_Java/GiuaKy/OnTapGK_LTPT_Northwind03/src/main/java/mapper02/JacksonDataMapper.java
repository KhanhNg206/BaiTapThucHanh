package mapper02;

import mapper.GenericDataMapper;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDataMapper implements GenericDataMapper {
    private ObjectMapper objectMapper;

    public JacksonDataMapper() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T toObject(Map<String, Object> data, Class<T> clazz) {
        return objectMapper.convertValue(data , clazz);
    }

    @Override
    public Map<String, Object> toMap(Object object) {
        return objectMapper.convertValue(object,Map.class);
    }
}
