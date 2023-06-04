package hexlet.code.Formatters;

import java.util.Map;
import java.util.TreeMap;

public interface Formatter {

    static Formatter createFormatter(String style) {
        switch (style) {
            case "stylish" -> {
                return new StylishFormatter();
            }
            case "plain" -> {
                return new PlainFormatter();
            }
            default -> throw new RuntimeException("Unsupported format");
        }
    }

    String format(TreeMap<String, Map<String, Object>> data);
}
