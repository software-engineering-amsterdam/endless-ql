package ExpressionEvaluator;

import ast.ASTBuilder;
import ast.model.Form;
import gui.model.QuestionModel;
import logic.collectors.CollectQuestionModelsVisitor;
import logic.evaluators.ExpressionEvaluator;
import grammar.QLLexer;
import grammar.QLParser;
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
    private List<QuestionModel> questionModels;

    @Before
    public void setUp() throws Exception {
        CharStream charStream = CharStreams.fromFileName("./test/forms/form0.qlform");
        QLLexer qlLexer = new QLLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
        QLParser qlParser = new QLParser(commonTokenStream);

        QLParser.FormContext formContext = qlParser.form();

        ASTBuilder astBuilder = new ASTBuilder();
        this.astForm = astBuilder.visitForm(formContext);

        CollectQuestionModelsVisitor collectQuestionModelsVisitor = new CollectQuestionModelsVisitor();
        this.astForm.accept(collectQuestionModelsVisitor);

        this.questionModels = collectQuestionModelsVisitor.getQuestionModels();
        this.evaluator = new ExpressionEvaluator(this.questionModels);

    }

    @Test
    public void simpleTest() {
        Assert.assertEquals(1, 1);
    }

//    @Test
//    public void testIncompatibleTypesExceptionMessage() {
//
//        for (QuestionModel formQuestion : this.questionModels) {
//            if (formQuestion.getAssignedExpression() != null) {
//                formQuestion.setValue(formQuestion.getAssignedExpression().accept(evaluator));
//            }
//            if (formQuestion.getVisibilityCondition() != null) {
//                formQuestion.setVisibility(formQuestion.getVisibilityCondition().accept(evaluator));
//            } else {
//                formQuestion.setVisibility(new ExpressionResult(Expression.DataType.BOOLEAN, true));
//            }
//        }
//    }
}
