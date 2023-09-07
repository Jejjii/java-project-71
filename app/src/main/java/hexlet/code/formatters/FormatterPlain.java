package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public class FormatterPlain {
    public static String formatOut(List<Diff> listDiff) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Diff diff : listDiff) {
            String stringVal1 = valueToString(diff.getVal1());

            String stringVal2 = valueToString(diff.getVal2());

            switch (diff.getAction()) {
                case "removed":
                    stringBuilder.append("Property '")
                            .append(diff.getKey())
                            .append("' was removed")
                            .append("\n");
                    break;
                case "added":
                    stringBuilder.append("Property '")
                            .append(diff.getKey())
                            .append("' was added with value: ")
                            .append(stringVal2)
                            .append("\n");
                    break;
                case "updated":
                    stringBuilder.append("Property '")
                            .append(diff.getKey())
                            .append("' was updated. From ")
                            .append(stringVal1)
                            .append(" to ")
                            .append(stringVal2)
                            .append("\n");
                    break;
                case "nothing":
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + diff.getAction());
            }
        }
        String stringBuilderString = stringBuilder.toString();


        return stringBuilderString.substring(0, stringBuilderString.length() - 1);
    }

    private static String valueToString(Object value) {
        String stringValue;
        if (value instanceof String) {
            stringValue = getStringValue(value);
        } else if (value instanceof Collection || value instanceof Map) {
            stringValue = "[complex value]";
        } else {
            stringValue = value.toString();
        }
        return stringValue;
    }

    private static String getStringValue(Object value) {
        String stringValue;
        if (!value.equals("null")) {
            stringValue = "'" + value + "'";
        } else {
            stringValue = value.toString();
        }
        return stringValue;
    }
}
