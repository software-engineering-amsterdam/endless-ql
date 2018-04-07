package ql.TypeChecker;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.ast.ASTBuilder;
import ql.ast.model.Form;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.logic.collectors.CollectConditionsVisitor;
import ql.logic.collectors.CollectQuestionsVisitor;
import ql.logic.collectors.CollectReferencesVisitor;
import ql.logic.validators.*;

public class TypeCheckerTest {

    private final CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
    private final CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
    private final CollectConditionsVisitor collectConditionsVisitor = new CollectConditionsVisitor();

    public static Form getAstFormFromString(String formString) {
        CharStream charStream = CharStreams.fromString(formString);
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);
        QLParser.FormContext formContext = qlParser.form();
        ASTBuilder astBuilder = new ASTBuilder();
        return astBuilder.visitForm(formContext);
    }

    @Test
    public void checkReferenceToUndefinedQuestionTest() {

        String fileContent = "form wrongForm1 {\n" +
                "\"How much money do you have?\"\n" +
                "    myBudget: money\n" +
                "  \"What is your private debt?\"\n" +
                "    generalPrivateDebt: money = (myBudget - privateDebt)\n" +
                "}";

        Form form = getAstFormFromString(fileContent);

        VariablesReferencesValidator validator = new VariablesReferencesValidator(
                this.collectQuestionsVisitor.getQuestions(form),
                this.collectReferencesVisitor.getVariableReferences(form)
        );

        Assert.assertFalse(validator.validate());
    }

    @Test
    public void checkDuplicateDefinitionsTest() {

        String fileContent = "form wrongForm2 {\n" +
                "\"How much money do you have?\"\n" +
                "    myBudget: money\n" +
                "  \"What is your budget?\"\n" +
                "    myBudget: string\n" +
                "}";

        Form form = getAstFormFromString(fileContent);
        
        QuestionsDuplicationValidator validator = new QuestionsDuplicationValidator(
                this.collectQuestionsVisitor.getQuestions(form)
        );

        Assert.assertFalse(validator.validate());
    }

    @Test
    public void checkDuplicateLabelsTest() throws RuntimeException {

        String fileContent = "form wrongForm3 {\n" +
                "\"How much money do you have?\"\n" +
                "    myBudget: money\n" +
                "  \"How much money do you have?\"\n" +
                "    myMoney: string\n" +
                "}";

        Form form = getAstFormFromString(fileContent);
        
        QuestionLabelsValidator validator = new QuestionLabelsValidator(
                this.collectQuestionsVisitor.getQuestions(form)
        );
        
        Assert.assertFalse(validator.validate());
    }

    @Test
    public void checkConditionsEvaluationTypeTest() {

        String fileContent = "form wrongForm4 {\n" +
                "  \"What is your age?\"\n" +
                "    age: integer\n" +
                "  if (age) {\n" +
                "    \"Do you drink alcohol?\"\n" +
                "    alcoholDrinker: boolean\n" +
                "  }\n" +
                "}";

        Form form = getAstFormFromString(fileContent);
        
        ConditionsValidator validator = new ConditionsValidator(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );
        
        Assert.assertFalse(validator.validate());

    }

    // invalid types to operators
    @Test
    public void checkInvalidStringIntegerTypesToOperatorsTest() throws RuntimeException {

        String fileContent = "form wrongForm5 {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "  if (name + age == 55) {\n" +
                "    \"Test\"\n" +
                "    test: string\n" +
                "  }\n" +
                "}";

        Form form = getAstFormFromString(fileContent);
        
        TypesValidator validator = new TypesValidator(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );

        Assert.assertFalse(validator.validate());
    }

    @Test
    public void checkInvalidIntegerBooleanTypesToOperatorsTest() throws RuntimeException {

        String fileContent = "form wrongForm5 {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "  if (age && debts) {\n" +
                "    \"Test\"\n" +
                "    test: string\n" +
                "  }\n" +
                "}";

        Form form = getAstFormFromString(fileContent);

        TypesValidator validator = new TypesValidator(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );

        Assert.assertFalse(validator.validate());

    }

    @Test
    public void checkInvalidTypesToOperatorsTest() throws RuntimeException {

        String fileContent = "form wrongForm5 {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "  if (income / debts) {\n" +
                "    \"Test\"\n" +
                "    test: string\n" +
                "  }\n" +
                "  \n" +
                "}";

        Form form = getAstFormFromString(fileContent);

        TypesValidator validator = new TypesValidator(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );

        Assert.assertFalse(validator.validate());

    }

    @Test
    public void checkInvalidOperationTest() throws RuntimeException {

        String fileContent = "form wrongForm5 {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "  if (\"one\" * name) {\n" +
                "    \"Test\"\n" +
                "    test: string\n" +
                "  }\n" +
                "}";

        Form form = getAstFormFromString(fileContent);

        TypesValidator validator = new TypesValidator(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );

        Assert.assertFalse(validator.validate());

    }

    @Test
    public void checkInvalidComparisionTypesToOperatorsTest() throws RuntimeException {

        String fileContent = "form wrongForm5 {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "  if (5 > name) {\n" +
                "    \"Test\"\n" +
                "    test: string\n" +
                "  }\n" +
                "\n" +
                "}";

        Form form = getAstFormFromString(fileContent);

        TypesValidator validator = new TypesValidator(
                this.collectConditionsVisitor.getConditions(form),
                this.collectQuestionsVisitor.getQuestions(form)
        );

        Assert.assertFalse(validator.validate());

    }

    @Test
    public void dependencyErrorTest() {

        String fileContent = "form dependencyErrorExample\n" +
                "{\n" +
                "  \"A\" a: integer = d + 1 \n" +
                "  \"B\" b: integer\n" +
                "  \"C\" c: integer = a + b\n" +
                "  \"D\" d: integer = c + 5\n" +
                "  \"E\" e: integer \n" +
                "  \"F\" f: integer = e + 10\n" +
                "}";

        Form form = getAstFormFromString(fileContent);


        QuestionsDependencyValidator validator = new QuestionsDependencyValidator(
                this.collectQuestionsVisitor.getQuestionsMap(form)
        );

        Assert.assertFalse(validator.validate());
    }

    @Test
    public void castingDecimalToIntegerErrorTest() {

        String fileContent = "form division\n" +
                "{\n" +
                "  \"Give me integer A\" intA: integer\n" +
                "  \"Give me decimal B\" decB: decimal\n" +
                "  \"A/B equals\" intC: integer = intA/decB\n" +
                "}";

        Form form = getAstFormFromString(fileContent);

        IntegerToDecimalCastingValidator validator = new IntegerToDecimalCastingValidator(
                this.collectQuestionsVisitor.getQuestionsMap(form)
        );

        Assert.assertFalse(validator.validate());
    }

}
