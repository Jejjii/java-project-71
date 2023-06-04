package hexlet.code.Formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PlainFormatter implements Formatter {
    @Override
    public String format(TreeMap<String, Map<String, Object>> data) {
        StringBuilder result = new StringBuilder();
        for (String key : data.keySet()) {
            Map<String, Object> elementInfo = data.get(key);
            if (elementInfo.get("status").equals("added")) {
                result.append(infoAdded(key, elementInfo.get("value")));
            } else if (elementInfo.get("status").equals("deleted")) {
                result.append(infoDeleted(key));
            } else if (elementInfo.get("status").equals("changed")) {
                result.append(infoChanged(key, elementInfo.get("oldValue"), elementInfo.get("newValue")));
            }
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 1); //deleting \n symbol
        }
        return result.toString();
    }

    private static String infoDeleted(String key) {
        return String.format("Property '%s' was removed\n", key);
    }

    private static String infoChanged(String key, Object oldValue, Object newValue) {
        return String.format("Property '%s' was updated. From %s to %s\n",
                key, valueFormatted(oldValue), valueFormatted(newValue));
    }

    private static String infoAdded(String key, Object value) {
        return String.format("Property '%s' was added with value: %s\n", key, valueFormatted(value));
    }

    private static String valueFormatted(Object value) {
        if (value == null) {
            return String.valueOf((Object) null);
        }
        if (value.getClass().equals(ArrayList.class) || value.getClass().equals(LinkedHashMap.class)) {
            return "[complex value]";
        } else if (value.getClass().equals(String.class)) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }
}
