package main;

import grammar.QLLexer;
import grammar.QLParser;
import gui.QLGui;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import validator.TypeChecker;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form1.ql");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        TypeChecker checker = new TypeChecker();

        checker.visitForm(formContext);

        checker.validate();

        //System.out.println(formContext.toStringTree(qlParser));

        System.out.println("done");

        /* Show the GUI */
       // new QLGui();


    }
}
