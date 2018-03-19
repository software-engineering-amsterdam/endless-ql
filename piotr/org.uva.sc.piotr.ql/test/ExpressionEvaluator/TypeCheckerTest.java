package ExpressionEvaluator;

import ast.ASTBuilder;
import ast.model.Form;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import exceptions.UndeclaredReferenceException;
import grammar.QLLexer;
import grammar.QLParser;
import logic.collectors.CollectQuestionModelsVisitor;
import logic.collectors.CollectQuestionsVisitor;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.ExpressionEvaluator;
import logic.validators.QuestionsDependencyValidator;
import logic.validators.VariablesReferencesValidator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;

public class TypeCheckerTest {

    private CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
    private CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();

    private Form getAstFormFromString(String formString) {
        CharStream charStream = CharStreams.fromString(formString);
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);
        QLParser.FormContext formContext = qlParser.form();
        ASTBuilder astBuilder = new ASTBuilder();
        return astBuilder.visitForm(formContext);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void checkReferenceToUndefinedQuestionTest() throws Exception {

        String formWithUndefinedReference = "form wrongForm1 {\n" +
                "\"How much money do you have?\"\n" +
                "    myBudget: money\n" +
                "  \"What is your private debt?\"\n" +
                "    generalPrivateDebt: money = (myBudget - privateDebt)\n" +
                "}";

        Form form = this.getAstFormFromString(formWithUndefinedReference);

        exception.expect(UndeclaredReferenceException.class);

        // Validate undeclared variables usage in questions and conditions
        VariablesReferencesValidator.validateVariablesUsage(
                this.collectQuestionsVisitor.getQuestions(form),
                this.collectReferencesVisitor.getVariableReferences(form)
        );
    }


}
