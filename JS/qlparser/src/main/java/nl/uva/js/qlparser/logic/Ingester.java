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

    public static Form toStringParsedForm(String qlForm) {
        return createFormFromLexer(new QLLexer(CharStreams.fromString(qlForm)));
    }

    public static Form toLocationParsedForm(String location) throws IOException {
        return createFormFromLexer(new QLLexer(CharStreams.fromPath(Paths.get(location))));
    }

    private static Form createFormFromLexer(QLLexer lexer) {
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        Form form = new QLFunnel().visitForm(parser.form());
        form.checkType();


        return form;
    }
}
