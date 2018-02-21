package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLLexer;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.models.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

@Component("ingester")
public class Ingester {

    public Form toParsedForm(String location) throws IOException {
        QLLexer lexer = new QLLexer(CharStreams.fromPath(Paths.get(location)));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        Form form = new QLFunnel().visitForm(parser.form());
        form.checkType();


        return form;
    }
}
