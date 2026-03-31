package mapper.Impl;

import mapper.GenericDataMapper;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDataMapper implements GenericDataMapper {
    private ObjectMapper objectMapper;

    public JacksonDataMapper( ) {
       objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T toObject(Map<String, Object> data, Class<T> clazz) {
        if(data == null){
            return null;
        }
        return objectMapper.convertValue(data,clazz);
    }

    @Override
    public Map<String, Object> toMap(Object object) {
        if(object == null){
            return null;
        }
        return objectMapper.convertValue(object,Map.class);
    }
}
