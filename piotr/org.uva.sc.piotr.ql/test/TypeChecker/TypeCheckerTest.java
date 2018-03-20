package TypeChecker;

import ast.ASTBuilder;
import ast.model.Form;
import exceptions.DuplicateDeclarationException;
import exceptions.DuplicateLabelException;
import exceptions.NonBooleanConditionException;
import exceptions.UndeclaredReferenceException;
import grammar.QLLexer;
import grammar.QLParser;
import logic.collectors.CollectConditionsVisitor;
import logic.collectors.CollectQuestionModelsVisitor;
import logic.collectors.CollectQuestionsVisitor;
import logic.collectors.CollectReferencesVisitor;
import logic.validators.ConditionsValidator;
import logic.validators.QuestionsValidator;
import logic.validators.VariablesReferencesValidator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TypeCheckerTest {

    private CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
    private CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
    private CollectConditionsVisitor collectConditionsVisitor = new CollectConditionsVisitor();
    private CollectQuestionModelsVisitor collectQuestionModelsVisitor = new CollectQuestionModelsVisitor();

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

    @Test
    public void checkDuplicateDefinitionsTest() throws Exception {

        String formWithUndefinedReference = "form wrongForm2 {\n" +
                "\"How much money do you have?\"\n" +
                "    myBudget: money\n" +
                "  \"What is your budget?\"\n" +
                "    myBudget: string\n" +
                "}";

        Form form = this.getAstFormFromString(formWithUndefinedReference);

        exception.expect(DuplicateDeclarationException.class);

        QuestionsValidator.validateDuplicates(this.collectQuestionsVisitor.getQuestions(form));
    }

    @Test
    public void checkDuplicateLabelsTest() throws RuntimeException {

        String formWithUndefinedReference = "form wrongForm3 {\n" +
                "\"How much money do you have?\"\n" +
                "    myBudget: money\n" +
                "  \"How much money do you have?\"\n" +
                "    myMoney: string\n" +
                "}";

        Form form = this.getAstFormFromString(formWithUndefinedReference);

        exception.expect(DuplicateLabelException.class);

        QuestionsValidator.validateLabels(this.collectQuestionsVisitor.getQuestions(form));

    }

    @Test
    public void checkConditionsEvaluationTypeTest() throws Exception {

        String formWithUndefinedReference = "form wrongForm4 {\n" +
                "  \"What is your age?\"\n" +
                "    age: integer\n" +
                "  if (age) {\n" +
                "    \"Do you drink alcohol?\"\n" +
                "    alcoholDrinker: boolean\n" +
                "  }\n" +
                "}";

        Form form = this.getAstFormFromString(formWithUndefinedReference);

        exception.expect(NonBooleanConditionException.class);

        ConditionsValidator.validateConditions(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );

    }

}
