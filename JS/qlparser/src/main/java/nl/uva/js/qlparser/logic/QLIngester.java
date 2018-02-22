package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLLexer;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.models.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class QLIngester {

    public static Form parseFormFromLocation(String location) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(location)));
        return parseFormFromString(content);
    }

    public static Form parseFormFromString(String qlInput) {
        QLLexer lexer = new QLLexer(CharStreams.fromString(qlInput));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        Form form = new QLFunnel().visitForm(parser.form());
        form.checkType();

        return form;
    }
}
