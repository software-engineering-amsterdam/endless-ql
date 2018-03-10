package main;

import ast.ASTBuilder;
import ast.model.Form;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.collectors.CollectFormStatementsVisitor;
import ast.visitors.collectors.CollectQuestionsVisitor;
import ast.visitors.collectors.CollectReferencesVisitor;
import grammar.QLLexer;
import grammar.QLParser;
import gui.QLGui;
import gui.model.FormBlock;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import validators.QuestionsDependencyValidator;
import validators.QuestionsValidator;
import validators.VariablesReferencesValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form3.qlform");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.visitForm(formContext);

        // list of references (?)
        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
        form.accept(collectReferencesVisitor);

        // questions graph for type validator
        CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
        form.accept(collectQuestionsVisitor);

        // Type checking
        HashMap<Question, ArrayList<VariableReference>> map = collectQuestionsVisitor.getQuestionsMap();
        QuestionsDependencyValidator questionsDependencyValidator = new QuestionsDependencyValidator(map);

        // undeclared variables usage
        VariablesReferencesValidator.validateVariablesUsage(
                collectQuestionsVisitor.getQuestions(),
                collectReferencesVisitor.getVariableReferences()
        );

        // duplicate question declarations with different types
        QuestionsValidator.validateDuplicates(collectQuestionsVisitor.getQuestions());

        // duplicate labels (warning)
        try {
            QuestionsValidator.validateLabels(collectQuestionsVisitor.getQuestions());
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
        }

        CollectFormStatementsVisitor collectFormStatementsVisitor = new CollectFormStatementsVisitor();
        form.accept(collectFormStatementsVisitor);

        List<FormBlock> formBlocks = collectFormStatementsVisitor.getFormBlocks();
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(form));



        System.out.println("Main finish.");

//        /* Show the GUI */
        new QLGui(formBlocks);


    }
}
