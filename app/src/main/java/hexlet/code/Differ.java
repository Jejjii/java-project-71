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
    public static List<Map.Entry<String, Object>> generate(String filepath1, String filepath2, String format) throws IOException {
        String format1 = getExtension(filepath1);
        String format2 = getExtension(filepath2); // Исправил ошибку: format2 = getExtension(filePath2);

        Path pathFile1 = Paths.get(filepath1);
        Path pathFile2 = Paths.get(filepath2);

        Map<String, Object> map1 = Parser.parseInMap(format1, readFileToString(pathFile1));
        Map<String, Object> map2 = Parser.parseInMap(format2, readFileToString(pathFile2));

        return getListOfDiff(map1, map2);
    }

    private static List<Map.Entry<String, Object>> getListOfDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Diff diff = new Diff();

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String key = entry.getKey();
            Object val1 = entry.getValue();
            Object val2 = map2.get(key);

            if (val2 == null) {
                diff.addChange(key, val1);
            } else if (!val2.equals(val1)) {
                diff.addChange(key,val1);
            }
        }

        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            String key = entry.getKey();
            Object val2 = entry.getValue();
            Object val1 = map1.get(key);

            if (val1 == null) {
                diff.addChange(key,val2);
            }
        }

        return diff.getChanges();
    }

    public static List<Map.Entry<String, Object>> generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
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
