package hexlet.code;

import hexlet.code.formatters.FormatterJson;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.io.IOException;

import java.util.List;

public class Formatter {
    public static String formatOut(String format, List<Diff> listDiff) throws IOException {
        return switch (format) {
            case "stylish" -> FormatterStylish.formatOut(listDiff);
            case "plain" -> FormatterPlain.formatOut(listDiff);
            case "json" -> FormatterJson.formatOut(listDiff);
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }
}

