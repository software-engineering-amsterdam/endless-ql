package main;

import models.ast.ASTBuilder;
import grammar.QLLexer;
import grammar.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Main {
    public static void main(String[] args) throws Exception {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form1.ql");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

//        TypeChecker checker = new TypeChecker();
//
//        checker.visitForm(formContext);
//
//        checker.validate();

//        System.out.println(formContext.toStringTree(qlParser));


        ASTBuilder ast = new ASTBuilder();
        ast.visitForm(formContext);

        ast.getForm().print();

        System.out.println("Main finish.");

        /* Show the GUI */
       // new QLGui();


    }
}
