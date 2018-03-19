
import domain.FormNode;
import domain.model.IfASTNode;
import domain.model.QuestionASTNode;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.MoneyValue;
import domain.model.value.StringValue;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import domain.model.variable.Variable;
import exception.InvalidArithmeticExpressionException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ASTTest {

    FormNode formNode;
    IfASTNode ifAstNode;
    QuestionASTNode qan1;
    QuestionASTNode qan2;
    Variable stringVariable;
    StringValue stringValue;

    Variable arithmeticExpressionVariable;

    Variable leftHandOperand;
    Variable rightHandOperand;

    MoneyValue leftHandOperandValue;
    MoneyValue rightHandOperandValue;

    String operator = "-";

    ArithmeticExpressionValue arithmeticExpressionValue;
    @Before
    public void init(){
        formNode = new FormNode();

        stringVariable = new StringVariable("testStringVariable");
        stringValue = new StringValue("testStringValue");
        stringVariable.setValue(stringValue);

        qan1 = new QuestionASTNode("testQAN1", stringVariable, false);
        ifAstNode = new IfASTNode(false);

        arithmeticExpressionVariable = new MoneyVariable("testMoneyVariable");

        leftHandOperand = new MoneyVariable("testLeftHandOperand");
        rightHandOperand = new MoneyVariable("testRightHandOperand");

        leftHandOperandValue = new MoneyValue(1000);
        rightHandOperandValue = new MoneyValue(500);

        leftHandOperand.setValue(leftHandOperandValue);
        rightHandOperand.setValue(rightHandOperandValue);

        arithmeticExpressionValue = new ArithmeticExpressionValue(leftHandOperand, rightHandOperand, operator);
        arithmeticExpressionVariable.setValue(arithmeticExpressionValue);
        qan2 = new QuestionASTNode("testQAN2", arithmeticExpressionVariable, false);

    }
    @Test
    public void StringVariableTest(){
        assertEquals("Test initialization for string variable", stringVariable.getIdentifier(), "testStringVariable");
        assertEquals("Test initialization for string value", stringValue.getValue(), "testStringValue");
        assertEquals("Test set value for string value", stringVariable.getValue(), stringValue);
    }
    @Test
    public void ArithmeticExpressionVariableTest(){
        assertEquals("Test set value for arithmetic expression value", arithmeticExpressionVariable.getValue(), arithmeticExpressionValue);
        assertEquals("Test compute value for arithmetic expressions", (Integer) arithmeticExpressionValue.getValue(), (Integer) 500);
    }

    @Test(expected = InvalidArithmeticExpressionException.class)
    public void TestInvalidArithmeticExpressionException() {
        arithmeticExpressionValue = new ArithmeticExpressionValue(stringVariable, rightHandOperand, operator);
        arithmeticExpressionValue.getValue();
    }


    @Test
    public void QuestionASTNodeTest(){
        assertEquals("Check if question ast node is correctly initialized", qan1.getText(), "testQAN1");
    }

    @Test
    public void IfASTNodeTest(){
        ifAstNode.addQuestion(qan2);
        assertEquals("Test if question is correctly added to if ast node list", ifAstNode.getQuestionNodes().get(0), qan2);
    }

    @Test
    public void FormNodeTest() {
        formNode.setFormIdentifier("TestNode");
        assertEquals("Return formIdentifier() test", formNode.getFormIdentifier(), "TestNode");
        assertEquals("Test if questions list is initiated correctly", formNode.getASTNodes().size(), 0);
        assertEquals("Test if referenced variables is initiated correctly", formNode.getReferencedVariables().size(), 0);

        formNode.addQuestion(qan1);
        assertEquals("Test if question is added correctly", formNode.getASTNodes().get(0), qan1);
        assertEquals("Check if variable is taken correcly from list by label", formNode.getVariableFromList("testStringVariable"), qan1.getVariable());
    }
}