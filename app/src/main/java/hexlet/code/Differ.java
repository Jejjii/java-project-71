package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filePath2, String format) throws IOException {
        String format1 = getExtension(filepath1);
        String format2 = getExtension(filepath1);

        Path pathFile1 = Paths.get(filepath1);
        Path pathFile2 = Paths.get(filePath2);

        Map<String, Object> map1 = Parser.parseInMap(format1, readFileToString(pathFile1));

        Map<String, Object> map2 = Parser.parseInMap(format2, readFileToString(pathFile2));

        List<Diff> listDiff = getListOfDiff(map1, map2);

        return Formatter.formatOut(format, listDiff);
    }

    private static List<Diff> getListOfDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> mapAll = new TreeMap<>();
        mapAll.putAll(map1);
        mapAll.putAll(map2);

        List<Diff> listDiff = new ArrayList<>();

        for (Map.Entry<String, Object> entry : mapAll.entrySet()) {
            String key = entry.getKey();

            Object val1 = (map1.get(key) == null) ? "null" : map1.get(key);
            Object val2 = (map2.get(key) == null) ? "null" : map2.get(key);

            if (!map2.containsKey(key)) {
                listDiff.add(new Diff(key, "removed", val1, val2));
            } else if (!map1.containsKey(key)) {
                listDiff.add(new Diff(key, "added", val1, val2));
            } else if (val2.equals(val1)) {
                listDiff.add(new Diff(key, "nothing", val1, val2));
            } else if (!val2.equals(val1)) {
                listDiff.add(new Diff(key, "updated", val1, val2));
            }
        }
        return listDiff;

    }

    public static String generate(String filepath1, String filePath2) throws IOException {
        return generate(filepath1, filePath2, "stylish");
    }

    static String readFileToString(Path pathFile) throws IOException {
        return Files.readString(pathFile);
    }

    static String getExtension(String filepath) {
        String extension = "";

        int i = filepath.lastIndexOf('.');
        if (i > 0) {
            extension = filepath.substring(i + 1);
        }
        return extension;
    }
}

