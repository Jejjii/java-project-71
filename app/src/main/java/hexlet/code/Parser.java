package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> parseInMap(String format, String contexString) throws IOException {
        ObjectMapper mapper;
        if (format.equals("yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            mapper = new ObjectMapper();
        }
        return mapper.readValue(contexString, TreeMap.class);
    }
}
