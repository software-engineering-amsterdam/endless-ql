package org.uva.forcepushql.tests.testBuildASTVisitor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.visitors.BuildASTVisitor;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class BuildASTVisitorTest {

    public static List<String> nodes(Node node, List<String> list)
    {
        list.add(node.getClass().getSimpleName());
        if (node instanceof FormNode){
            for (Node n: ((FormNode) node).getQuestions()) {
                list = nodes(n,list);
            }
        }

        if(node instanceof ConditionalNode){
            if(((ConditionalNode) node).getCondition() != null){
                list.add(((ConditionalNode) node).getCondition().getClass().getSimpleName());
            }

            for (Node n: ((ConditionalNode) node).getQuestions()){
                list = nodes(n,list);
            }

            if (((ConditionalNode) node).getAfter() != null){
                list = nodes(((ConditionalNode) node).getAfter(),list);
            }
        }

        if (node instanceof QuestionNode){
            list.add(((QuestionNode) node).getLeft().getClass().getSimpleName());
            list.add(((QuestionNode) node).getCenter().getClass().getSimpleName());
            list.add(((QuestionNode) node).getRight().getClass().getSimpleName());
        }

        if (node instanceof QuestionAssignValueNode){
            list = nodes(((QuestionAssignValueNode) node).getPrevious(),list);
            list.add(((QuestionAssignValueNode) node).getExpression().getClass().getSimpleName());

        }


        return list;

    }

    private GrammarParser makeGrammarParser(String input) throws IOException {
        GrammarLexer grammarLexer = new GrammarLexer(new ANTLRInputStream(new StringReader(input)));
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);
        return new GrammarParser(commonTokenStream);

    }

    @Test
    public void buildASTQuestionFormat() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");

        GrammarParser grammarParser = makeGrammarParser("\"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: boolean");
        Node node = new BuildASTVisitor().visitQuestionFormat(grammarParser.questionFormat());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTQuestionAssignValueFormat() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("QuestionAssignValueNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("SubtractionNode");

        GrammarParser grammarParser = makeGrammarParser("\"Value residue:\"\n" +
                "          valueResidues: money =\n" +
                "           (sellingPrice - privateDebt)");
        Node node = new BuildASTVisitor().visitQuestionAssignValue(grammarParser.questionAssignValue());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTConditionalIfFormat() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("ConditionalNode");
        original.add("NameNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");

        GrammarParser grammarParser = makeGrammarParser(" if (hasSoldHouse) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money }");
        Node node = new BuildASTVisitor().visitConditionalIf(grammarParser.conditionalIf());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTFullConditionalFormat() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("ConditionalNode");
        original.add("NameNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionAssignValueNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("SubtractionNode");
        original.add("ConditionalNode");
        original.add("NameNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionAssignValueNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("AdditionNode");
        original.add("ConditionalNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");

        GrammarParser grammarParser = makeGrammarParser(" if (hasSoldHouse) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money =\n" +
                "       (sellingPrice - privateDebt)\n" +
                "    }\n" +
                "\n" +
                "    ifelse(hasBoughtHouse){\n" +
                "        \"What was the selling price?\"\n" +
                "          sellingPrice: money\n" +
                "        \"Private debts for the sold house:\"\n" +
                "          privateDebt: money\n" +
                "        \"Value residue:\"\n" +
                "          valueResidues: money =\n" +
                "           (sellingPrice + privateDebt)\n" +
                "    }\n" +
                "\n" +
                "    else{\n" +
                "        \"Are you ok?\"\n" +
                "        isOk: boolean\n" +
                "    }");
        Node node = new BuildASTVisitor().visitConditionalIf(grammarParser.conditionalIf());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

    @Test
    public void buildASTFormFormat() throws IOException {
        LinkedList<String> original = new LinkedList<>();
        original.add("FormNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");
        original.add("QuestionNode");
        original.add("LabelNode");
        original.add("NameNode");
        original.add("TypeNode");

        GrammarParser grammarParser = makeGrammarParser("form taxOfficeExample {\n" +
                "  \"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: boolean\n" +
                "  \"Did you buy a house in 2010?\"\n" +
                "    hasBoughtHouse: boolean\n" +
                "  \"Did you enter a loan?\"\n" +
                "    hasMaintLoan: boolean}");
        Node node = new BuildASTVisitor().visitFormStructure(grammarParser.formStructure());
        List<String> toTest = nodes(node, new LinkedList<String>());

        Assert.assertEquals(original, toTest);
    }

}
