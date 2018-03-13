package ql.parser;

import ql.model.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.visitor.VisitorForm;

import java.io.IOException;
import java.io.InputStream;

public class FormParser {

    public static Form parseForm(InputStream stream) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        // Walk it and attach our listener
        VisitorForm visitor = new VisitorForm();

        return visitor.visit(parser.root());
    }
}