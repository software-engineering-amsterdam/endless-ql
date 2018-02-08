package main.org.uva.ql.app;

import generated.org.uva.ql.parser.QLLexer;
import generated.org.uva.ql.parser.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;


public class App {

    public static void main (String [] args) {
        try {
            CharStream cs = CharStreams.fromFileName("input/default.ql");
            QLLexer lexer = new QLLexer(cs);

            QLParser parser = new QLParser(new CommonTokenStream(lexer));
            QLParser.FormContext fctx =  parser.form();
        }
        catch (IOException ex) {

        }


    }
}
