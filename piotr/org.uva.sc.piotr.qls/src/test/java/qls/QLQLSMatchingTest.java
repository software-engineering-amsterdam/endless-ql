package qls;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;
import ql.ast.ASTBuilder;
import ql.ast.model.Form;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import qls.ast.model.Stylesheet;
import qls.grammar.QLSLexer;
import qls.grammar.QLSParser;
import qls.logic.validators.AllQuestionsCoveredValidator;
import qls.logic.validators.NoEmptyReferencesValidator;
import qls.logic.validators.NoMultipleQuestionDeclarationValidator;

@SuppressWarnings("Duplicates")
public class QLQLSMatchingTest {

    private static Form getAstFormFromString(String formString) {
        CharStream charStream = CharStreams.fromString(formString);
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);
        QLParser.FormContext formContext = qlParser.form();
        ASTBuilder astBuilder = new ASTBuilder();
        return astBuilder.visitForm(formContext);
    }


    private static Stylesheet getStylesheetASTFromString(String stylesheetString) {
        CharStream charStream = CharStreams.fromString(stylesheetString);
        QLSLexer qlsLexer = new QLSLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlsLexer);
        QLSParser qlsParser = new QLSParser(commonTokenStream);
        QLSParser.StylesheetContext stylesheetContext = qlsParser.stylesheet();
        qls.ast.ASTBuilder astBuilder = new qls.ast.ASTBuilder();
        return astBuilder.visitStylesheet(stylesheetContext);
    }

    @Test
    public void checkReferencesTrueTest() {

        String formString = "form test {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "}";

        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page PersonalData\n" +
                "  {\n" +
                "    section \"Name\"\n" +
                "    {\n" +
                "      question name  \n" +
                "        widget text \n" +
                "    }\n" +
                "    section \"Age\"  \n" +
                "      question age\n" +
                "      widget spinbox\n" +
                "  }\n" +
                "\n" +
                "  page Finances\n" +
                "  { \n" +
                "    section \"Selling\"\n" +
                "    {\n" +
                "      question income\n" +
                "      \n" +
                "      section \"Debts\"\n" +
                "      {\n" +
                "        question debts\n" +
                "      }\n" +
                "    }\n" +
                "    default boolean widget radio(\"Yes\", \"No\")\n" +
                "  }  \n" +
                "}";

        Form form = getAstFormFromString(formString);
        Stylesheet stylesheet = getStylesheetASTFromString(stylesheetString);

        AllQuestionsCoveredValidator validator = new AllQuestionsCoveredValidator(form, stylesheet);
        boolean result = validator.validate();
        Assert.assertTrue(result);
    }

    @Test
    public void checkReferencesFalseTest() {

        String formString = "form test {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "}";

        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page PersonalData\n" +
                "  {\n" +
                "    section \"Name\"\n" +
                "    {\n" +
                "      question name  \n" +
                "        widget text \n" +
                "    }\n" +
                "    section \"Age\"  \n" +
                "      question age\n" +
                "      widget spinbox\n" +
                "  }\n" +
                "\n" +
                "  page Finances\n" +
                "  { \n" +
                "    section \"Selling\"\n" +
                "    {\n" +
                "      question income\n" +
                "      \n" +
                "    }\n" +
                "    default boolean widget radio(\"Yes\", \"No\")\n" +
                "  }  \n" +
                "}";

        Form form = getAstFormFromString(formString);
        Stylesheet stylesheet = getStylesheetASTFromString(stylesheetString);

        AllQuestionsCoveredValidator validator = new AllQuestionsCoveredValidator(form, stylesheet);
        boolean result = validator.validate();
        Assert.assertFalse(result);
    }

    @Test
    public void emptyReferencesInQLSTest() {

        String formString = "form test {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "}";

        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page PersonalData\n" +
                "  {\n" +
                "    section \"Name\"\n" +
                "    {\n" +
                "      question name  \n" +
                "        widget text \n" +
                "      question nickname  \n" +
                "        widget text \n" +
                "    }\n" +
                "    section \"Age\"  \n" +
                "      question age\n" +
                "      widget spinbox\n" +
                "  }\n" +
                "\n" +
                "  page Finances\n" +
                "  { \n" +
                "    section \"Selling\"\n" +
                "    {\n" +
                "      question income\n" +
                "      \n" +
                "    }\n" +
                "    default boolean widget radio(\"Yes\", \"No\")\n" +
                "  }  \n" +
                "}";

        Form form = getAstFormFromString(formString);
        Stylesheet stylesheet = getStylesheetASTFromString(stylesheetString);

        NoEmptyReferencesValidator validator = new NoEmptyReferencesValidator(form, stylesheet);
        boolean result = validator.validate();
        Assert.assertFalse(result);
    }

    @Test
    public void noEmptyReferencesInQLSTest() {

        String formString = "form test {\n" +
                "  \"What is your name?\"\n" +
                "  name: string\n" +
                "  \"What is your age?\"\n" +
                "  age: integer\n" +
                "  \"What is your monthly income?\"\n" +
                "  income: decimal\n" +
                "  \"Do you have debts?\"\n" +
                "  debts: boolean\n" +
                "\n" +
                "}";

        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page PersonalData\n" +
                "  {\n" +
                "    section \"Name\"\n" +
                "    {\n" +
                "      question name  \n" +
                "        widget text \n" +
                "    }\n" +
                "    section \"Age\"  \n" +
                "      question age\n" +
                "      widget spinbox\n" +
                "  }\n" +
                "\n" +
                "  page Finances\n" +
                "  { \n" +
                "    section \"Selling\"\n" +
                "    {\n" +
                "      question income\n" +
                "      \n" +
                "    }\n" +
                "    default boolean widget radio(\"Yes\", \"No\")\n" +
                "  }  \n" +
                "}";

        Form form = getAstFormFromString(formString);
        Stylesheet stylesheet = getStylesheetASTFromString(stylesheetString);

        NoEmptyReferencesValidator validator = new NoEmptyReferencesValidator(form, stylesheet);
        boolean result = validator.validate();
        Assert.assertTrue(result);
    }

    @Test
    public void noDuplicateQuestionDeclarationTest() {

        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page PersonalData\n" +
                "  {\n" +
                "    section \"Name\"\n" +
                "    {\n" +
                "      question name  \n" +
                "        widget text \n" +
                "    }\n" +
                "    section \"Age\"  \n" +
                "      question age\n" +
                "      widget spinbox\n" +
                "  }\n" +
                "\n" +
                "}";

        Stylesheet stylesheet = getStylesheetASTFromString(stylesheetString);

        NoMultipleQuestionDeclarationValidator validator
                = new NoMultipleQuestionDeclarationValidator(stylesheet);

        boolean result = validator.validate();
        Assert.assertTrue(result);
    }

    @Test
    public void duplicateQuestionDeclarationTest() {

        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page PersonalData\n" +
                "  {\n" +
                "    section \"Name\"\n" +
                "    {\n" +
                "      question name  \n" +
                "        widget text \n" +
                "    }\n" +
                "    section \"Age\"  \n" +
                "      question age\n" +
                "      widget spinbox\n" +
                "  }\n" +
                "\n" +
                "  page Finances\n" +
                "  { \n" +
                "    section \"Selling\"\n" +
                "    {\n" +
                "      question income\n" +
                "      question name  \n" +
                "        widget text \n" +
                "      \n" +
                "    }\n" +
                "    default boolean widget radio(\"Yes\", \"No\")\n" +
                "  }  \n" +
                "}";

        Stylesheet stylesheet = getStylesheetASTFromString(stylesheetString);

        NoMultipleQuestionDeclarationValidator validator
                = new NoMultipleQuestionDeclarationValidator(stylesheet);

        boolean result = validator.validate();
        Assert.assertFalse(result);
    }
}
