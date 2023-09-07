package hexlet.code.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    static String readFileToString(Path pathFile) throws IOException {
        return Files.readString(pathFile);
    }
}
