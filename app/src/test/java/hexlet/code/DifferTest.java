package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DifferTest {

    private static String stylishResult;
    private static String plainResult;
    private static String jsonResult;

    @BeforeAll
    public static void generate() throws IOException {
        stylishResult = getData(buildCommonPath("results/stylish.txt"));
        plainResult = getData(buildCommonPath("results/plain.txt"));
        jsonResult = getData(buildCommonPath("results/json.txt"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml", "yml"})
    public final void diffTest(String fileType) throws IOException {
        String filePath1 = buildCommonPath(fileType) + "1." + fileType;
        String filePath2 = buildCommonPath(fileType) + "2." + fileType;

        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(stylishResult);
        assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualTo(stylishResult);
        assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualTo(plainResult);
        assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualTo(jsonResult);
    }

    private static String getData(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static String buildCommonPath(String fileName) {
        return "src/test/resources/fixtures/" + fileName;
    }

    @Test
    public void diffTestDiffExtensions() throws IOException {
        String filePath1 = buildCommonPath("json") + "1." + "json";
        String filePath2 = buildCommonPath("yaml") + "2." + "yaml";
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(stylishResult);
    }

    @Test
    public void diffTestWrongExtension() {
        String filePath1 = buildCommonPath("json") + "1." + "json";
        String filePath2 = buildCommonPath("wrongextension.md");

        assertThatThrownBy(() -> {
            Differ.generate(filePath1, filePath2);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageMatching("Unsupported extension");
    }
}
