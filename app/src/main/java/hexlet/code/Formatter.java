package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String getFormattedString(List<Map<String, Object>> diffs, String formatName)
            throws JsonProcessingException, IllegalArgumentException {

        return switch (formatName) {
            case "plain" -> Plain.getFormattedDiffers(diffs);
            case "json"  -> Json.getFormattedDiffers(diffs);
            case "stylish" -> Stylish.getFormattedDiffers(diffs);
            default -> throw new IllegalArgumentException("Illegal format: " + formatName);
        };

    }

}
