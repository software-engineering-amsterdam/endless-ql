package main;

import ast.ASTBuilder;
import ast.model.Form;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import grammar.QLLexer;
import grammar.QLParser;
import gui.QLGui;
import gui.model.FormQuestionHolder;
import logic.collectors.CollectFormQuestionHoldersVisitor;
import logic.collectors.CollectQuestionsVisitor;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.ExpressionEvaluator;
import logic.validators.QuestionsDependencyValidator;
import logic.validators.QuestionsValidator;
import logic.validators.VariablesReferencesValidator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream charStream = CharStreams.fromFileName("./example-ql/form4.qlform");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.visitForm(formContext);

        // Collect all references from all expressions in the form (both: assignments and conditions)
        CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
        form.accept(collectReferencesVisitor);
        ArrayList<VariableReference> references = collectReferencesVisitor.getVariableReferences();

        // Collect all questions
        CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
        form.accept(collectQuestionsVisitor);
        ArrayList<Question> questions = collectQuestionsVisitor.getQuestions();

        // Validate questions against cyclic dependency @TODO: finish
        HashMap<Question, ArrayList<VariableReference>> questionsMap = collectQuestionsVisitor.getQuestionsMap();
        QuestionsDependencyValidator questionsDependencyValidator = new QuestionsDependencyValidator(questionsMap);

        // Validate undeclared variables usage in questions and conditions
        VariablesReferencesValidator.validateVariablesUsage(questions, references);

        // Validate duplicate question declarations with different types
        QuestionsValidator.validateDuplicates(questions);

        // Validate duplicate labels (warning)
        try {
            QuestionsValidator.validateLabels(questions);
        } catch (Exception e) {
            System.out.println("Warning: " + e.getMessage());
        }

        // TODO: validate conditions that are not of the type boolean

        // TODO: operands of invalid type to operators

        CollectFormQuestionHoldersVisitor collectFormQuestionHoldersVisitor = new CollectFormQuestionHoldersVisitor();
        form.accept(collectFormQuestionHoldersVisitor);

        List<FormQuestionHolder> formQuestionHolders = collectFormQuestionHoldersVisitor.getFormQuestionHolders();
        ExpressionEvaluator evaluator = new ExpressionEvaluator(collectFormQuestionHoldersVisitor.getVariablesValues());

        new QLGui(form.getName(), formQuestionHolders, evaluator);

        System.out.println("Main finish.");


    }
}
