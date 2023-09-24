package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;
import java.util.TreeMap;

public final class JSONFormatter implements Formatter {
    @Override
    public String format(TreeMap<String, Map<String, Object>> data) {
        ObjectMapper writer = new ObjectMapper();
        writer.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return writer.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Something went horribly wrong in JSONFormatter!");
        }
    }
}

