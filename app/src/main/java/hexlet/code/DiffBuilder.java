package hexlet.code;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class DiffBuilder {
    static TreeMap<String, Map<String, Object>> getDifference(Map<String, Object> map1,
                                                                      Map<String, Object> map2) {
        TreeMap<String, Map<String, Object>> result = new TreeMap<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> elementInfo = new LinkedHashMap<>();
            if (!map1.containsKey(key)) {
                elementInfo.put("status", "added");
                elementInfo.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                elementInfo.put("status", "deleted");
                elementInfo.put("value", map1.get(key));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                elementInfo.put("status", "changed");
                elementInfo.put("value1", map1.get(key));
                elementInfo.put("value2", map2.get(key));
            } else {
                elementInfo.put("status", "same");
                elementInfo.put("value", map1.get(key));
            }
            result.put(key, elementInfo);
        }
        return result;
    }
}

