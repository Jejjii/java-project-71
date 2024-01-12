package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {

    private static ObjectMapper getObjectMapper(String formatName) {
        ObjectMapper mapper = null;
        if (formatName.endsWith("json")) {
            mapper = new ObjectMapper();
        } else if (formatName.endsWith("yml") || formatName.endsWith("yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Illegal format: " + formatName);
        }
        return mapper;
    }

    public static Map<String, Object> getDataStructureFromFile(String strFromFile,
                                                               String formatName) throws IOException {
        ObjectMapper mapper = getObjectMapper(formatName);
        return mapper.readValue(strFromFile, new TypeReference<>() { });
    }

}
