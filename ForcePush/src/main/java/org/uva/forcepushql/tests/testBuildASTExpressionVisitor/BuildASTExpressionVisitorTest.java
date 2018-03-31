package org.uva.forcepushql.tests.testBuildASTExpressionVisitor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.visitors.BuildASTExpressionVisitor;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class BuildASTExpressionVisitorTest {

    public static List<String> nodes(ExpressionNode node, List<String> list)
    {
        list.add(node.getClass().getSimpleName());

        if(node instanceof InfixExpressionNode){
            list = nodes(((InfixExpressionNode) node).getLeft(),list);
            list = nodes(((InfixExpressionNode) node).getRight(),list);
        }


        return list;

    }

    private GrammarParser makeGrammarParser(String input) throws IOException {
        GrammarLexer grammarLexer = new GrammarLexer(new ANTLRInputStream(new StringReader(input)));
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);
        return new GrammarParser(commonTokenStream);

    }

    @Test
    public void buildASTSimpleMathExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("SubtractionNode");
        original.add("AdditionNode");
        original.add("NumberNode");
        original.add("NumberNode");
        original.add("DecimalNode");


        GrammarParser grammarParser = makeGrammarParser("45 + 89 - 23.8");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTMathExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("DivisionNode");
        original.add("MultiplicationNode");
        original.add("SubtractionNode");
        original.add("AdditionNode");
        original.add("NumberNode");
        original.add("NumberNode");
        original.add("NumberNode");
        original.add("VariableNode");
        original.add("VariableNode");

        GrammarParser grammarParser = makeGrammarParser("((45 + 89 - 23) * soldPrice ) / totalPrice");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTLogicalExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("OrNode");
        original.add("AndNode");
        original.add("VariableNode");
        original.add("VariableNode");
        original.add("VariableNode");


        GrammarParser grammarParser = makeGrammarParser("(hasSoldHouse && hasBoughtHouse) || hasMaintLoan");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTCompareEqualLessExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("EqualLessNode");
        original.add("NumberNode");
        original.add("NumberNode");


        GrammarParser grammarParser = makeGrammarParser("52 <= 100");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTCompareLessExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("LessNode");
        original.add("NumberNode");
        original.add("NumberNode");


        GrammarParser grammarParser = makeGrammarParser("4235 < 100000");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTCompareGreaterExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("GreaterNode");
        original.add("NumberNode");
        original.add("NumberNode");


        GrammarParser grammarParser = makeGrammarParser("100000 > 4235");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTCompareEqualGreaterExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("EqualGreaterNode");
        original.add("NumberNode");
        original.add("NumberNode");


        GrammarParser grammarParser = makeGrammarParser("100 >= 52");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTCompareNotEqualExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("NotEqualNode");
        original.add("DecimalNode");
        original.add("NumberNode");


        GrammarParser grammarParser = makeGrammarParser("100.45 != 52");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTCompareIsEqualExpression() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("IsEqualNode");
        original.add("VariableNode");
        original.add("DecimalNode");

        GrammarParser grammarParser = makeGrammarParser("housePrice == 5275.69");
        ExpressionNode node = new BuildASTExpressionVisitor().visitMathUnit(grammarParser.mathUnit());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }
}
