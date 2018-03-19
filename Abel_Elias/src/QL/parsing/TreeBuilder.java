package QL.parsing;

import QLS.parsing.QLSLexer;
import QLS.parsing.QLSParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import QL.parsing.gen.QLLexer;
import QL.parsing.gen.QLParser;

import java.io.IOException;
import java.io.InputStream;

public class TreeBuilder {

    public QLParser.FormContext build(InputStream inputStream) throws IOException {

        //Call the lexer and get the tokens
        QLLexer lexer = new QLLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tokens/tree
        QLParser parser = new QLParser(tokens);
        return parser.form();
    }

    public QLSParser.StylesheetContext buildQls(InputStream inputStream) throws IOException {
        QLSLexer lexer = new QLSLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tokens/tree
        QLSParser parser = new QLSParser(tokens);
        return parser.stylesheet();
    }
}
