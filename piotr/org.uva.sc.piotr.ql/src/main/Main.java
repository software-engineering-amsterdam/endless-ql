package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ast.ASTBuilder;
import grammar.QLLexer;
import grammar.QLParser;
import ast.model.Form;
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

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.visitForm(formContext);

        // Type checker
//        form.accept(TypeChecker);


        Gson gsonBuilder = new GsonBuilder().create();
        String jsonForm = gsonBuilder.toJson(form);

        System.out.println(jsonForm);



        System.out.println("Main finish.");

        /* Show the GUI */
       // new QLGui();


    }
}
