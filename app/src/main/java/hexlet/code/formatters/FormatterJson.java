package hexlet.code.formatters;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hexlet.code.Diff;

import java.io.IOException;
import java.util.List;


public class FormatterJson {

    public static String formatOut(List<Diff> listDiff) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        return writer.writeValueAsString(listDiff);

    }
}
