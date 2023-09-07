package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;
import static java.lang.System.out;


@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", help = true)
    private boolean help;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", help = true)
    private boolean version;

    @Override
    public Integer call() throws IOException {
        if (help) {
            CommandLine.usage(this, out, CommandLine.Help.Ansi.AUTO);
        }
        if (filepath1 != null) {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
