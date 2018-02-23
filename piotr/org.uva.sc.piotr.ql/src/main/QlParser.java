package main;

import antlr.QLLexer;
import antlr.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class QlParser {

    public static void main (String [] args) throws IOException {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form1.ql");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        System.out.println("done");

    }

}
