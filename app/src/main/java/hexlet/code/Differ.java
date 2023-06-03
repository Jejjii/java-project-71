package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {
    public static String generate(String path1, String path2) throws IOException {
        String data1 = getData(path1);
        String data2 = getData(path2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap1 = objectMapper.readValue(data1, new TypeReference<>() { });
        Map<String, Object> dataMap2 = objectMapper.readValue(data2, new TypeReference<>() { });

        TreeMap<String, Map<String, Object>> difference = getDifference(dataMap1, dataMap2);
        return format(difference);
    }

    private static String format(TreeMap<String, Map<String, Object>> data) {
        StringBuilder result = new StringBuilder("{\n");
        for (String key : data.keySet()) {
            Map<String, Object> elementInfo = data.get(key);
            if (elementInfo.get("status").equals("added")) {
                result.append("  + ").append(key).append(": ").append(elementInfo.get("value")).append("\n");
            } else if (elementInfo.get("status").equals("deleted")) {
                result.append("  - ").append(key).append(": ").append(elementInfo.get("value")).append("\n");
            } else if (elementInfo.get("status").equals("changed")) {
                result.append("  - ").append(key).append(": ").append(elementInfo.get("oldValue")).append("\n");
                result.append("  + ").append(key).append(": ").append(elementInfo.get("newValue")).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(elementInfo.get("value")).append("\n");
            }
        }
        return result.append("}").toString();
    }

    private static String getData(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static TreeMap<String, Map<String, Object>> getDifference(Map<String, Object> map1,
                                                                      Map<String, Object> map2) {
        TreeMap<String, Map<String, Object>> result = new TreeMap<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> elementInfo = new HashMap<>();
            if (!map1.containsKey(key)) {
                elementInfo.put("status", "added");
                elementInfo.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                elementInfo.put("status", "deleted");
                elementInfo.put("value", map1.get(key));
            } else if (!map1.get(key).equals(map2.get(key))) {
                elementInfo.put("status", "changed");
                elementInfo.put("oldValue", map1.get(key));
                elementInfo.put("newValue", map2.get(key));
            } else {
                elementInfo.put("status", "same");
                elementInfo.put("value", map1.get(key));
            }
            result.put(key, elementInfo);
        }
        return result;
    }
}
