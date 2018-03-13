package ExpressionEvaluator;

import ast.ASTBuilder;
import ast.model.Form;
import logic.collectors.CollectFormQuestionHoldersVisitor;
import logic.evaluators.ExpressionEvaluator;
import grammar.QLLexer;
import grammar.QLParser;
import gui.model.FormQuestionHolder;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.util.List;

public class ExpressionEvaluatorTest {

    private Form astForm;
    private ExpressionEvaluator evaluator;
    private List<FormQuestionHolder> formQuestionHolders;

    @Before
    public void setUp() throws Exception {
        CharStream charStream = CharStreams.fromFileName("./test/forms/form0.qlform");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        ASTBuilder astBuilder = new ASTBuilder();
        this.astForm = astBuilder.visitForm(formContext);

        CollectFormQuestionHoldersVisitor collectFormQuestionHoldersVisitor = new CollectFormQuestionHoldersVisitor();
        this.astForm.accept(collectFormQuestionHoldersVisitor);

        this.formQuestionHolders = collectFormQuestionHoldersVisitor.getFormQuestionHolders();
        this.evaluator = new ExpressionEvaluator(collectFormQuestionHoldersVisitor.getVariablesValues());

    }

    @Test
    public void simpleTest() {
        Assert.assertEquals(1, 1);
    }

//    @Test
//    public void testIncompatibleTypesExceptionMessage() {
//
//        for (FormQuestionHolder formQuestion : this.formQuestionHolders) {
//            if (formQuestion.getAssignedExpression() != null) {
//                formQuestion.setValueHolder(formQuestion.getAssignedExpression().accept(evaluator));
//            }
//            if (formQuestion.getVisibilityCondition() != null) {
//                formQuestion.setVisibilityHolder(formQuestion.getVisibilityCondition().accept(evaluator));
//            } else {
//                formQuestion.setVisibilityHolder(new ExpressionResult(Expression.DataType.BOOLEAN, true));
//            }
//        }
//    }
}
