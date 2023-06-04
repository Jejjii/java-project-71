package hexlet.code.Formatters;

import java.util.Map;
import java.util.TreeMap;

public interface Formatter {
    String format(TreeMap<String, Map<String, Object>> data);
}
