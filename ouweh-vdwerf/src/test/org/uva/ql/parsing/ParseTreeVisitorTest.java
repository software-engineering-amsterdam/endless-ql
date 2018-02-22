package org.uva.ql.parsing;

import antlr.generated.QLParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.expression.ParameterGroup;
import org.uva.ql.ast.expression.binary.And;
import org.uva.ql.ast.expression.binary.Or;
import org.uva.ql.ast.expression.unary.BooleanLiteral;
import org.uva.ql.ast.expression.unary.IntegerLiteral;
import org.uva.ql.ast.expression.unary.StringLiteral;
import org.uva.ql.ast.expression.unary.Parameter;

import java.util.Arrays;
import java.util.List;


public class ParseTreeVisitorTest {

    private ASTBuilder builder;

    @Before
    public void setUp() throws Exception {
        this.builder = new ASTBuilder();
    }

    @Test
    public void visitForm() {
    }

    @Test
    public void visitQuestion() {
    }

    @Test
    public void visitIfStatement() {
    }

    @Test
    public void visitIfElseStatement() {
    }

    @Test
    public void visitAddSub() {
        List<String> testCases = Arrays.asList(
                "1 + 1",
                "1 - 1",
                "( ( 1 - 1 ) + 1 ) - 1"
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Expression expression = builder.getExpression(parser);
            Assert.assertEquals(testCase, expression.toString());
        }
    }

    @Test
    public void visitComparation() {
        List<String> testCases = Arrays.asList(
                "1 > 1",
                "( 1 > 1 ) > 1",
                "1 < 1",
                "( 1 < 1 ) < 1",
                "1 >= 1",
                "( 1 >= 1 ) >= 1",
                "1 <= 1",
                "( 1 <= 1 ) <= 1",
                "1 != 1",
                "( 1 != 1 ) != 1",
                "1 == 1",
                "( 1 == 1 ) == 1",
                "( 1 < 1 ) >= ( ( ( 1 < 1 ) > ( 1 == 1 ) ) != 1 ) == 1"
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Expression expression = builder.getExpression(parser);
            Assert.assertEquals(testCase, expression.toString());
        }
    }

    @Test
    public void visitIntegerType() {
    }

    @Test
    public void visitBooleanType() {
    }

    @Test
    public void visitStringType() {
    }

    @Test
    public void visitParameter() {
        List<String> testCases = Arrays.asList(
                "P",
                "P",
                "Parameter",
                "PARAMETER"
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Parameter parameter = (Parameter) builder.getExpression(parser);
            Assert.assertEquals(testCase, parameter.toString());
        }
    }

    @Test
    public void visitStringLiteral() {
        List<String> testCases = Arrays.asList(
                "\"a\"",
                "\"ABC\"",
                "\"TestTestTestTestTestTest\""
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            StringLiteral stringLiteral = (StringLiteral) builder.getExpression(parser);
            Assert.assertEquals(testCase, stringLiteral.toString());
        }
    }

    @Test
    public void visitBooleanLiteral() {
        List<String> testCases = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            BooleanLiteral booleanLiteral = (BooleanLiteral) builder.getExpression(parser);
            Assert.assertEquals(testCase.toLowerCase(), booleanLiteral.toString());
        }
    }

    @Test
    public void visitIntegerLiteral() {
        List<String> testCases = Arrays.asList(
                "-1",
                "0",
                "1",
                "10",
                "2147483647" // Max signed positive int
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            IntegerLiteral integerLiteral = (IntegerLiteral) builder.getExpression(parser);
            Assert.assertEquals(testCase, integerLiteral.toString());
        }
    }

    @Test
    public void visitLogicalOr() {
        QLParser parser = builder.getQLParser("A || B");
        Or or = (Or) builder.getExpression(parser);
        Assert.assertEquals("A OR B", or.toString());
    }

    @Test
    public void visitLogicalAnd() {
        QLParser parser = builder.getQLParser("A && B");
        And and = (And) builder.getExpression(parser);
        Assert.assertEquals("A AND B", and.toString());
    }

    @Test
    public void visitCalculatedValue() {
    }

    @Test
    public void visitParameterGroup() {
        QLParser parser = builder.getQLParser("( 1 )");
        ParameterGroup parameterGroup = (ParameterGroup) builder.getExpression(parser);
        Assert.assertEquals("( 1 )", parameterGroup.toString());

    }

    @Test
    public void visitMulDiv() {
        List<String> testCases = Arrays.asList(
                "1 * 1",
                "1 / 1"
        );
        for (String testCase: testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Expression expression = builder.getExpression(parser);
            Assert.assertEquals(testCase, expression.toString());
        }
    }
}