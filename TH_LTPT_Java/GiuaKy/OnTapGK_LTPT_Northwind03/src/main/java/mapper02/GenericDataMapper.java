package mapper02;

import java.util.Map;
import java.util.Objects;

public interface GenericDataMapper {
    Map<String,Object> toMap(Object objects);
    <T> T toObject(Map<String,Object> data, Class<T> clazz);
}
