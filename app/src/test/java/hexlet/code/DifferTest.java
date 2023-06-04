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
                          - follow: false
                            host: hexlet.io
                          - proxy: 123.234.53.22
                          - timeout: 50
                          + timeout: 20
                          + verbose: true
                        }""";

    @Test
    void diffTestSimple() throws IOException {
        assertThat(Differ.generate(json1, json2)).isEqualTo(result);
    }

    @Test
    void diffTestAbsolute() throws IOException {
        String absolute1 = Paths.get(json1).toAbsolutePath().normalize().toString();
        String absolute2 = Paths.get(json2).toAbsolutePath().normalize().toString();
        assertThat(Differ.generate(absolute1, absolute2)).isEqualTo(result);
    }

    @Test
    void diffTestYaml() throws IOException {
        assertThat(Differ.generate(yaml1, yaml2)).isEqualTo(result);
    }

    @Test
    void diffTestExtensions() throws IOException {
        assertThat(Differ.generate(json1, yaml2)).isEqualTo(result);
    }
}
