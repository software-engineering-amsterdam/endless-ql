package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLLexer;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.models.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Paths;

public class Ingester {

    public Form toParsedForm(String location) throws IOException {
        QLLexer lex = new QLLexer(CharStreams.fromPath(Paths.get(location)));
        QLParser parser = new QLParser(new CommonTokenStream(lex));
        parser.getState();
        Form form = new QLFunnel().visitForm(parser.form());



        return form;
    }
}
