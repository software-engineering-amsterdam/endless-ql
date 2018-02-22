package main;

import grammar.QLBaseListener;
import grammar.QLLexer;
import grammar.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import validator.TypeListener;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form1.ql");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.UnitContext unitContext = qlParser.unit();

        ParseTreeWalker walker = new ParseTreeWalker();
        TypeListener listener = new TypeListener();

        walker.walk(listener, unitContext);

        System.out.println("done");

    }
}
