package hexlet.code;

import hexlet.code.Formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String path1, String path2, String style) throws IOException {
        String data1 = getData(path1);
        String data2 = getData(path2);

        Map<String, Object> dataMap1 = Parser.parseData(getExtension(path1), data1);
        Map<String, Object> dataMap2 = Parser.parseData(getExtension(path2), data2);

        TreeMap<String, Map<String, Object>> difference = DiffBuilder.getDifference(dataMap1, dataMap2);
        return Formatter.createFormatter(style).format(difference);
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }

    private static String getData(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static String getExtension(String path) {
        String[] temp = path.split("\\.");
        return temp[temp.length - 1];
    }
}
