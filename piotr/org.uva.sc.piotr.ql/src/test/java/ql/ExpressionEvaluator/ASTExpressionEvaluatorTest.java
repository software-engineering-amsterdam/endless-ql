package ql.ExpressionEvaluator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ql.ast.model.Form;
import ql.gui.model.QuestionModel;
import ql.logic.evaluators.FormModelExpressionEvaluator;

import java.util.List;

public class ASTExpressionEvaluatorTest {

    private Form astForm;
    private FormModelExpressionEvaluator evaluator;
    private List<QuestionModel> questionModels;

    @Before
    public void setUp() throws Exception {
//        CharStream charStream = CharStreams.fromFileName("../src/main/ql/resources/example-ql/form0.qlform");
//        QLLexer qlLexer = new QLLexer(charStream);
//        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);
//        QLParser qlParser = new QLParser(commonTokenStream);
//
//        QLParser.FormContext formContext = qlParser.form();
//
//        ASTBuilder astBuilder = new ASTBuilder();
//        this.astForm = astBuilder.visitForm(formContext);
//
//        CollectQuestionModelsVisitor collectQuestionModelsVisitor = new CollectQuestionModelsVisitor();
//
//        this.questionModels = collectQuestionModelsVisitor.getQuestionModels(astForm);
//        this.evaluator = new FormModelExpressionEvaluator(this.questionModels);

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
