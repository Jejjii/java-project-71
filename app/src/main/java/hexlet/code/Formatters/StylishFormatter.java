package hexlet.code.Formatters;

import java.util.Map;
import java.util.TreeMap;

public final class StylishFormatter implements Formatter {

    @Override
    public String format(TreeMap<String, Map<String, Object>> data) {
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
}
