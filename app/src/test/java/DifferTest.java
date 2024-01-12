import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {

    private static String stylishFormatReferenceStr;
    private static String plainFormatReferenceStr;
    private static String jsonFormatReferenceStr;

    @BeforeAll
    static void loadAllReferenceStrings() throws IOException {
        stylishFormatReferenceStr = readStringFromFile(getTestFilePath("StylishFormatReferenceStr.txt"));
        plainFormatReferenceStr = readStringFromFile(getTestFilePath("PlainFormatReferenceStr.txt"));
        jsonFormatReferenceStr = readStringFromFile(getTestFilePath("JsonFormatReferenceStr.txt"));
    }

    static String readStringFromFile(String fileNameOrFullPath) throws IOException {
        Path absolutePath = Paths.get(fileNameOrFullPath).toAbsolutePath().normalize();
        return Files.readString(absolutePath);
    }

    static String getTestFilePath(String filename) {
        return System.getProperty("user.dir") + "/src/test/resources/" + filename;
    }

    String getTestStr(String filename1, String filename2, String format) throws IOException {
        String filepath1 = getTestFilePath(filename1);
        String filepath2 = getTestFilePath(filename2);

        String testStr = "";
        testStr = Differ.generate(filepath1, filepath2, format);

        return testStr;
    }

    String getTestStr(String filename1, String filename2) throws IOException {
        String filepath1 = getTestFilePath(filename1);
        String filepath2 = getTestFilePath(filename2);

        String testStr = "";
        testStr = Differ.generate(filepath1, filepath2);

        return testStr;
    }

    @Test
    void jsonToStylishFormatTest() throws IOException {
        String rezStr = getTestStr("file1.json", "file2.json", "stylish");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToStylishFormatTest() throws IOException {
        String rezStr = getTestStr("file1.yml", "file2.yml", "stylish");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

    @Test
    void jsonToPlainFormatTest() throws IOException {
        String rezStr = getTestStr("file1.json", "file2.json", "plain");
        assertEquals(plainFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToPlainFormatTest() throws IOException {
        String rezStr = getTestStr("file1.yml", "file2.yml", "plain");
        assertEquals(plainFormatReferenceStr, rezStr);
    }

    @Test
    void jsonToJsonFormatTest() throws IOException {
        String rezStr = getTestStr("file1.json", "file2.json", "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expected = mapper.readTree(jsonFormatReferenceStr);
        JsonNode actual = mapper.readTree(rezStr);
        assertEquals(expected, actual);
    }

    @Test
    void ymlToJsonFormatTest() throws IOException {
        String rezStr = getTestStr("file1.yml", "file2.yml", "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expected = mapper.readTree(jsonFormatReferenceStr);
        JsonNode actual = mapper.readTree(rezStr);
        assertEquals(expected, actual);
    }

    @Test
    void jsonToDefaultFormatTest() throws IOException {
        String rezStr = getTestStr("file1.json", "file2.json");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToDefaultFormatTest() throws IOException {
        String rezStr = getTestStr("file1.yml", "file2.yml");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

}
