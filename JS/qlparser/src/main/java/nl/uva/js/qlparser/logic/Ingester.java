package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLLexer;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.models.Form;
import nl.uva.js.qlparser.models.enums.DataType;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

public class Ingester {

    public Form toParsedForm(String location) throws IOException {
        QLLexer lexer = new QLLexer(CharStreams.fromPath(Paths.get(location)));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        Form form = new QLFunnel().visitForm(parser.form());
        form.checkType();


        return form;
    }
}
