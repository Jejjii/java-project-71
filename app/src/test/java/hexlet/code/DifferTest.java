package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testDifferfile1andfile2() throws IOException {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(getFullPath("file1.json"), getFullPath("file2.json"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferfile1andfile1() throws IOException {
        String expected = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";
        String actual = Differ.generate(getFullPath("file1.json"), getFullPath("file1.json"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile2() throws IOException {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual;
        actual = Differ.generate(getFullPath("fileYML1.yml"), getFullPath("fileYML2.yml"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile1() throws IOException {
        String expected = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";
        String actual;
        actual = Differ.generate(getFullPath("fileYML1.yml"), getFullPath("fileYML1.yml"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferBigFile1andFile2() throws IOException {
        String expected = Files.readString(Paths.get(getFullPath("stylishExpect.txt")));
        String actual;
        actual = Differ.generate(getFullPath("file1Big.json"), getFullPath("file2Big.json"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLBigFile1andFile2() throws IOException {
        String expected = Files.readString(Paths.get(getFullPath("stylishExpect.txt")));
        String actual;
        actual = Differ.generate(getFullPath("file1BigYML.yml"), getFullPath("file2BigYML.yml"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLBigFile1andFile2NoFormat() throws IOException {
        String expected = Files.readString(Paths.get(getFullPath("stylishExpect.txt")));
        String actual;
        actual = Differ.generate(getFullPath("file1BigYML.yml"), getFullPath("file2BigYML.yml"));
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferPlainBigFile1andFile2() throws IOException {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String actual;
        actual = Differ.generate(getFullPath("file1Big.json"), getFullPath("file2Big.json"), "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferJSONBigFile1andFile2() throws IOException {

        String expected = Files.readString(Paths.get(getFullPath("JsonExpect.json")));
        String actual;
        actual = Differ.generate(getFullPath("file1Big.json"), getFullPath("file2Big.json"), "json");
        assertEquals(expected, actual);
    }

    public static String getFullPath(String fileName) {
        return "src/test/resources/" + fileName;
    }
}
