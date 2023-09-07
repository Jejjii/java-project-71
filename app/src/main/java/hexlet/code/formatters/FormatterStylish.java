package hexlet.code.formatters;

import hexlet.code.Diff;
import java.util.List;

public class FormatterStylish {
    public static String formatOut(List<Diff> listDiff) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (Diff diff : listDiff) {

            String val1String = diff.getVal1().toString();
            String val2String = diff.getVal2().toString();
            stringBuilder.append("  ");
            switch (diff.getAction()) {
                case "removed" -> stringBuilder.append("- " + diff.getKey())
                        .append(": ")
                        .append(val1String)
                        .append("\n");
                case "added" -> stringBuilder.append("+ " + diff.getKey())
                        .append(": ")
                        .append(val2String)
                        .append("\n");
                case "nothing" -> stringBuilder.append("  " + diff.getKey())
                        .append(": ")
                        .append(val2String)
                        .append("\n");
                case "updated" -> stringBuilder.append("- " + diff.getKey())
                        .append(": ")
                        .append(val1String)
                        .append("\n")
                        .append("  ")
                        .append("+ " + diff.getKey())
                        .append(": ")
                        .append(val2String)
                        .append("\n");
                default -> throw new IllegalStateException("Unexpected value: " + diff.getAction());
            }
        }
        stringBuilder.append("}");
        return (stringBuilder.toString());
    }
}
