package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private final String filepath1 = "src/test/resources/json1";
    private final String filepath2 = "src/test/resources/json2";

    @Test
    void diffTestSimple() throws IOException {
        String result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(Differ.generate(filepath1, filepath2)).isEqualTo(result);
    }

    @Test
    void diffTestAbsolute() throws IOException {
        String absolute1 = Paths.get(filepath1).toAbsolutePath().normalize().toString();
        String absolute2 = Paths.get(filepath2).toAbsolutePath().normalize().toString();
        String result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(Differ.generate(filepath1, filepath2)).isEqualTo(result);
    }
}
