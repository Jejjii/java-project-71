package hexlet.code;

import hexlet.code.Formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class Differ {
    public static String generate(String path1, String path2, String style) throws IOException {
        String data1 = getData(path1);
        String data2 = getData(path2);

        Map<String, Object> dataMap1 = Parser.parseData(getExtension(path1), data1);
        Map<String, Object> dataMap2 = Parser.parseData(getExtension(path2), data2);

        TreeMap<String, Map<String, Object>> difference = getDifference(dataMap1, dataMap2);
        return Formatter.createFormatter(style).format(difference);
    }

    private static String getData(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static String getExtension(String path) {
        String[] temp = path.split("\\.");
        return temp[temp.length - 1];
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
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
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
