package parsing;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parsing.gen.QLLexer;
import parsing.gen.QLParser;
import parsing.visitors.InitVisitor;

import java.io.IOException;
import java.io.InputStream;

public class TreeBuilder {

    public QLParser.FormContext build(InputStream inputStream) throws IOException {

        //Call the lexer and get the tokens
        QLLexer lexer = new QLLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tokens/tree
        QLParser parser = new QLParser(tokens);
        return parser.form(); }
}
