package main;

import ast.model.expressions.unary.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.filters.QuestionsFilter;
import ast.visitors.filters.ReferencesFilter;
import ast.ASTBuilder;
import grammar.QLLexer;
import grammar.QLParser;
import ast.model.Form;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import validators.QuestionsValidator;
import validators.VariablesReferencesValidator;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form1.qlform");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.visitForm(formContext);

        // questions graph for type validator
        QuestionsFilter questionsFilter = new QuestionsFilter();
        form.accept(questionsFilter);

        // list of references (?)
        ReferencesFilter referencesFilter = new ReferencesFilter();
        form.accept(referencesFilter);

        // Type checking
        HashMap<Question, ArrayList<VariableReference>> map = questionsFilter.getQuestionsMap();

        // undeclared variables usage
        VariablesReferencesValidator.validateVariablesUsage(
                questionsFilter.getQuestions(),
                referencesFilter.getVariableReferences()
        );

        // duplicate question declarations with different types
        QuestionsValidator.validateDuplicates(questionsFilter.getQuestions());

        // duplicate labels (warning)
        try {
            QuestionsValidator.validateLabels(questionsFilter.getQuestions());
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
        }

        System.out.println("Main finish.");

        /* Show the GUI */
       // new QLGui();


    }
}
