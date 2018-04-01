package org.uva.forcepushql.tests.testExpressions;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;
import org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.elements.ExpressionNode;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;
import org.uva.forcepushql.parser.ast.visitors.BuildASTExpressionVisitor;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ASTExpressionVisitorEvaluatorTest
{


    private GrammarParser makeGrammarParser(String input) throws IOException {
        File testFile = new File(input);
        InputStream stream1 = new FileInputStream(testFile);
        ANTLRInputStream inputStream = new ANTLRInputStream(stream1);
        GrammarLexer lexer = new GrammarLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new GrammarParser(tokens);
    }

    @Test
    public void subtractionCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput1.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        double result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((SubtractionNode) node);

        Assert.assertEquals(2.0, result,0.0);
    }

    @Test
    public void subtractionDivideCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput2.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        double result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((SubtractionNode) node);

        Assert.assertEquals(2.0, result,0.0);
    }

    @Test
    public void multiplicationSubtractionCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput3.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        double result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((MultiplicationNode) node);

        Assert.assertEquals(4.0, result,0.0);
    }

    @Test
    public void negateCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput4.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((NotNode) node);

        Assert.assertEquals(true, result);
    }

    @Test
    public void logicalCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput5.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((OrNode) node);

        Assert.assertEquals(true, result);
    }

    @Test
    public void greaterCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput6.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((GreaterNode) node);

        Assert.assertEquals(false, result);
    }

    @Test
    public void lessCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput7.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((LessNode) node);

        Assert.assertEquals(true, result);
    }

    @Test
    public void equalGreaterCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput8.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((EqualGreaterNode) node);

        Assert.assertEquals(true, result);
    }

    @Test
    public void equalLessCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput9.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((EqualLessNode) node);

        Assert.assertEquals(false, result);
    }

    @Test
    public void notEqualCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput10.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((NotEqualNode) node);

        Assert.assertEquals(false, result);
    }

    @Test
    public void isEqualCalculation() throws IOException
    {
        GrammarParser parser = makeGrammarParser("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput11.txt");
        ExpressionNode  node = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = new org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator().visit((IsEqualNode) node);

        Assert.assertEquals(true, result);
    }

}
