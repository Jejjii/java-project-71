package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private final String json1 = "src/test/resources/json1.json";
    private final String json2 = "src/test/resources/json2.json";

    private final String yaml1 = "src/test/resources/yaml1.yaml";
    private final String yaml2 = "src/test/resources/yaml2.yaml";

    private final String result = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    @Test
    void diffTestSimple() throws IOException {
        assertThat(Differ.generate(json1, json2, "stylish")).isEqualTo(result);
    }

    @Test
    void diffTestAbsolute() throws IOException {
        String absolute1 = Paths.get(json1).toAbsolutePath().normalize().toString();
        String absolute2 = Paths.get(json2).toAbsolutePath().normalize().toString();
        assertThat(Differ.generate(absolute1, absolute2, "stylish")).isEqualTo(result);
    }

    @Test
    void diffTestYaml() throws IOException {
        assertThat(Differ.generate(yaml1, yaml2, "stylish")).isEqualTo(result);
    }

    @Test
    void diffTestExtensions() throws IOException {
        assertThat(Differ.generate(json1, yaml2, "stylish")).isEqualTo(result);
    }

    @Test
    void diffTestPlain() throws IOException {
        String plainResult = """
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
        assertThat(Differ.generate(json1, json2, "plain")).isEqualTo(plainResult);
    }
}
