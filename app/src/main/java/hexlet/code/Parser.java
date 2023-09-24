package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parseData(String extension, String data) throws JsonProcessingException {
        ObjectMapper mapper;
        if (extension.equals("json")) {
            mapper = new JsonMapper();
        } else if (extension.equals("yaml") || extension.equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new RuntimeException("Unsupported extension");
        }

        return mapper.readValue(data, new TypeReference<>() { });
    }
}

