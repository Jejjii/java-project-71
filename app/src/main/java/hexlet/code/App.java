package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "0.7",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file",
            defaultValue = "file1.json")
            //defaultValue = "")
    private File filepath1 = new File("");

    @Parameters(index = "1", description = "path to second file",
            defaultValue = "file2.json")
            //defaultValue = "")
    private File filepath2 = new File("");

    @CommandLine.Option(names = {"-f", "--format"}, description = "[default: stylish]")
    private String format = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {

        try {
            System.out.println(Differ.generate(filepath1.toString(), filepath2.toString(), format.toLowerCase()));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }

    }
}
