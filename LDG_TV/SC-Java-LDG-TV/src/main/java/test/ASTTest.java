package test;

import domain.model.ast.ConditionNode;
import domain.model.ast.FormNode;
import domain.model.ast.QuestionNode;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.MoneyValue;
import domain.model.value.StringValue;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import domain.model.variable.Variable;
import exception.DuplicateQuestionDeclarationException;
import exception.InvalidArithmeticExpressionException;
import exception.ReferenceUndefinedVariableException;
import junit.framework.TestCase;
import loader.QL.QLChecker;
import org.junit.Before;
import org.junit.Test;

public class ASTTest {

    FormNode formNode;
    ConditionNode conditionNode;
    QuestionNode qan1;
    QuestionNode qan2;
    Variable stringVariable;
    StringValue stringValue;

    Variable arithmeticExpressionVariable;

    Variable leftHandOperand;
    Variable rightHandOperand;

    MoneyValue leftHandOperandValue;
    MoneyValue rightHandOperandValue;

    String operator = "-";

    ArithmeticExpressionValue arithmeticExpressionValue;
    QLChecker qlChecker;

    @Before
    public void init() {
        formNode = new FormNode();
        qlChecker = new QLChecker(formNode);
        stringVariable = new StringVariable("testStringVariable", "testValue");
        stringValue = new StringValue("testStringValue");
//        stringVariable.setValue(stringValue);

        qan1 = new QuestionNode("testQAN1", stringVariable, false);
        conditionNode = new ConditionNode(false);

        arithmeticExpressionVariable = new MoneyVariable("testMoneyVariable", 0);

        leftHandOperand = new MoneyVariable("testLeftHandOperand", 50);
        rightHandOperand = new MoneyVariable("testRightHandOperand", 20);

        leftHandOperandValue = new MoneyValue(1000);
        rightHandOperandValue = new MoneyValue(500);

//        leftHandOperand.setValue(leftHandOperandValue);
//        rightHandOperand.setValue(rightHandOperandValue);

        arithmeticExpressionValue = new ArithmeticExpressionValue(leftHandOperand, rightHandOperand, operator);
        arithmeticExpressionVariable.setValue(arithmeticExpressionValue);
        qan2 = new QuestionNode("testQAN2", arithmeticExpressionVariable, false);

    }

    @Test
    public void StringVariableTest() {
        TestCase.assertEquals("Test initialization for string variable", stringVariable.getIdentifier(), "testStringVariable");
        TestCase.assertEquals("Test initialization for string value", stringValue.getValue(), "testStringValue");
        TestCase.assertEquals("Test set value for string value", stringVariable.getComputedValue(), stringValue);
    }

    @Test
    public void ArithmeticExpressionVariableTest() {
        TestCase.assertEquals("Test set value for arithmetic expression value", arithmeticExpressionVariable.getComputedValue(), arithmeticExpressionValue);
        TestCase.assertEquals("Test compute value for arithmetic expressions", (Integer) arithmeticExpressionValue.getValue(), (Integer) 500);
    }

    @Test(expected = InvalidArithmeticExpressionException.class)
    public void TestInvalidArithmeticExpressionException() {
        arithmeticExpressionValue = new ArithmeticExpressionValue(stringVariable, rightHandOperand, operator);
        arithmeticExpressionValue.getValue();
    }

    @Test(expected = DuplicateQuestionDeclarationException.class)
    public void TestDuplicateQuestionDeclarationException() throws DuplicateQuestionDeclarationException {
        formNode.addQuestion(qan1);
        formNode.addQuestion(qan1);
        qlChecker.checkDuplicateQuestionDeclaration();
    }

    @Test(expected = ReferenceUndefinedVariableException.class)
    public void TestReferenceUndefinedVariableException() throws ReferenceUndefinedVariableException {
        Variable undefinedVariable = formNode.getVariableFromList("undefined");
        formNode.getReferencedVariables().add(undefinedVariable);
        qlChecker.checkReferenceUndefinedVariable();
    }

    @Test
    public void QuestionASTNodeTest() {
        TestCase.assertEquals("Check if question ast node is correctly initialized", qan1.getText(), "testQAN1");
    }

    @Test
    public void IfASTNodeTest() {
        conditionNode.addQuestion(qan2);
        TestCase.assertEquals("Test if question is correctly added to if ast node list", conditionNode.getQuestionNodes().get(0), qan2);
    }

    @Test
    public void FormNodeTest() {
        formNode.setFormIdentifier("TestNode");
        TestCase.assertEquals("Return formIdentifier() test", formNode.getFormIdentifier(), "TestNode");
        TestCase.assertEquals("Test if questions list is initiated correctly", formNode.getASTNodes().size(), 0);
        TestCase.assertEquals("Test if referenced variables is initiated correctly", formNode.getReferencedVariables().size(), 0);

        formNode.addQuestion(qan1);
        TestCase.assertEquals("Test if question is added correctly", formNode.getASTNodes().get(0), qan1);
        TestCase.assertEquals("Check if variable is taken correcly from list by label", formNode.getVariableFromList("testStringVariable"), qan1.getVariable());
    }
}