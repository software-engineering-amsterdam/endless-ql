package main;

import ast.visitors.QuestionsGraph;
import ast.visitors.ReferencesList;
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

        // questions graph for type validator
        QuestionsGraph graph = new QuestionsGraph();
        form.accept(graph);

        // list of references (?)
        ReferencesList referencesList = new ReferencesList();
        form.accept(referencesList);

        // Type checking

        // undeclared variables usage
        referencesList.validateWithGraph(graph);

        // duplicate question declarations with different types
        graph.validateDuplicates();

        // duplicate labels (warning)
        try {
            graph.validateLabels();
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
        }

        System.out.println("Main finish.");

        /* Show the GUI */
       // new QLGui();


    }
}
