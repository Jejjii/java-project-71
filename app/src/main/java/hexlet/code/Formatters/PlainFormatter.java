package hexlet.code.Formatters;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(TreeMap<String, Map<String, Object>> data) {
        StringBuilder result = new StringBuilder();
        for (String key : data.keySet()) {
            Map<String, Object> elementInfo = data.get(key);
            if (elementInfo.get("status").equals("added")) {
                result.append(String.format("Property '%s' was added with value: %s\n",
                        key, valueFormatted(elementInfo.get("value"))));
            } else if (elementInfo.get("status").equals("deleted")) {
                result.append(String.format("Property '%s' was removed\n", key));
            } else if (elementInfo.get("status").equals("changed")) {
                result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        key, valueFormatted(elementInfo.get("value1")), valueFormatted(elementInfo.get("value2"))));
            }
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 1); //deleting \n symbol
        }
        return result.toString();
    }

    private static String valueFormatted(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }
}
