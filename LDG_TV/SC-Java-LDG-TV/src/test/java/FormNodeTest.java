
import domain.FormNode;
import domain.model.QuestionASTNode;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.MoneyValue;
import domain.model.value.StringValue;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import domain.model.variable.Variable;
import exception.InvalidArithmeticExpressionException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FormNodeTest {

    FormNode formNode;

    QuestionASTNode qan;

    Variable stringVariable;
    StringValue stringValue;

    Variable expressionVariable;
    Variable leftHandOperand;
    MoneyValue leftHandOperandValue;
    Variable rightHandOperand;
    MoneyValue rightHandOperandValue;
    String operator = "-";

    ArithmeticExpressionValue arithmeticExpressionValue;
    @Before
    public void init(){
        stringVariable = new StringVariable("testStringVariable");
        stringValue = new StringValue("testStringValue");
        stringVariable.setValue(stringValue);

        expressionVariable = new MoneyVariable("testMoneyVariable");

        leftHandOperand = new MoneyVariable("testLeftHandOperand");
        rightHandOperand = new MoneyVariable("testRightHandOperand");

        leftHandOperandValue = new MoneyValue(1000);
        rightHandOperandValue = new MoneyValue(500);

        leftHandOperand.setValue(leftHandOperandValue);
        rightHandOperand.setValue(rightHandOperandValue);

        arithmeticExpressionValue = new ArithmeticExpressionValue(leftHandOperand, rightHandOperand, operator);

    }
    @Test
    public void StringVariableTest(){
        assertEquals("Test initialization for string variable", stringVariable.getIdentifier(), "testStringVariable");
        assertEquals("Test initalization for string value", stringValue.getValue(), "testStringValue");
        assertEquals("Test set value for string variable", stringVariable.getValue(), stringValue);
    }
    @Test
    public void ArithmeticExpressionVariableTest(){
        assertEquals("Test compute value for arithmetic expressions", (Integer) arithmeticExpressionValue.getValue(), (Integer) 500);
    }
    @Test(expected = InvalidArithmeticExpressionException.class)
    public void TestInvalidArithmeticExpressionException() {
        arithmeticExpressionValue = new ArithmeticExpressionValue(stringVariable, rightHandOperand, operator);
        arithmeticExpressionValue.getValue();
    }


    @Test
    public void QuestionASTNodeTest(){
        qan = new QuestionASTNode("testQAN", stringVariable, false);
        assertEquals("Check if question ast node is correctly initialized", qan.getText(), "testQAN");
    }

    @Test
    public void FormNodeTest() {
        formNode = new FormNode(); // MyClass is tested
        formNode.setFormIdentifier("TestNode");
        assertEquals("Return formIdentifier() test", formNode.getFormIdentifier(), "TestNode");
        assertEquals("Test if questions list is initiated correctly", formNode.getASTNodes().size(), 0);
        assertEquals("Test if referenced variables is initiated correctly", formNode.getReferencedVariables().size(), 0);

        formNode.getASTNodes().add(qan);
        assertEquals("Test if question is added correctly", formNode.getASTNodes().get(0), qan);
        assertEquals("Check if variable is taken correcly from list by label", formNode.getVariableFromList("testQAN"), qan);

    }
}