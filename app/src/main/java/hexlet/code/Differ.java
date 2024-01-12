package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.getDataStructureFromFile;

public class Differ {

    private static String readStringFromFile(String fileNameOrFullPath) throws IOException {
        Path absolutePath = Paths.get(fileNameOrFullPath).toAbsolutePath().normalize();
        return Files.readString(absolutePath);
    }

    private static String getFormatName(String filePath) {
        String[] extArray = filePath.split("\\.");
        if (extArray.length > 0) {
            return extArray[extArray.length - 1];
        } else {
            return "";
        }
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> treeMap1 = getDataStructureFromFile(readStringFromFile(filePath1),
                getFormatName(filePath1));
        Map<String, Object> treeMap2 = getDataStructureFromFile(readStringFromFile(filePath2),
                getFormatName(filePath2));

        List<Map<String, Object>> treeMapsDifferences = Diff.getTreeMapsDifferencesList(treeMap1, treeMap2);
        return Formatter.getFormattedString(treeMapsDifferences, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
}
