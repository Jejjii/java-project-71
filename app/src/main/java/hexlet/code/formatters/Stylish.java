package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String getFormattedDiffers(List<Map<String, Object>> diffs) {
        StringBuilder rezStr = new StringBuilder("{\n");

        for (Map diff: diffs) {
            switch (diff.get("Difference").toString()) {
                case "removed"    -> rezStr.append("  - " + diff.get("key") + ": " + diff.get("value") + "\n");
                case "notChanged" -> rezStr.append("    " + diff.get("key") + ": " + diff.get("value") + "\n");
                case "updated"  -> {
                    rezStr.append("  - " + diff.get("key") + ": " + diff.get("file1Value") + "\n");
                    rezStr.append("  + " + diff.get("key") + ": " + diff.get("file2Value") + "\n");
                }
                default  -> rezStr.append("  + " + diff.get("key") + ": " + diff.get("value") + "\n");
            }
        }

        rezStr.append("}");
        return rezStr.toString();
    }

}
