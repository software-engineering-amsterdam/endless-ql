package org.uva.forcepushql;

import org.antlr.v4.runtime.*;
import org.uva.forcepushql.antlr.GrammarLexer;
import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.ast.BuildASTVisitor;
import org.uva.forcepushql.ast.Node;
import org.uva.forcepushql.ast.ASTVisitorEvaluator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        File testFile = new File("src/main/resources/antlr/TestInput.txt");
        InputStream stream = new FileInputStream(testFile);
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(stream);
        // create a lexer that feeds off of input CharStream
        GrammarLexer lexer = new GrammarLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        GrammarParser parser = new GrammarParser(tokens);
        // begin parsing at rule x
        //Node expression = new BuildASTVisitor().visitMathUnit(parser.mathUnit());
        //String value = new EvaluateExpressionVisitor().visit(expression);
        //Node question = new BuildASTVisitor().visitQuestionFormat(parser.questionFormat());
        //String value = new EvaluateExpressionVisitor().visit(question);
        //Node ifCondition = new BuildASTVisitor().visitConditionalIf(parser.conditionalIf());
        //String value = new EvaluateExpressionVisitor().visit(ifCondition);
        Node form = new BuildASTVisitor().visitFormStructure(parser.formStructure());
        //String value = form.accept(new ASTVisitorEvaluator());

        JFrame guiFrame = new JFrame("MainFrame");
        guiFrame.setLocationRelativeTo(null);

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Questionnaire");
        guiFrame.setSize(720,720);

        guiFrame.setLayout(null);

        //ADD PANELS
        LinkedList<JPanel> jPanels = form.accept(new ASTVisitorEvaluator());
        Insets insets = guiFrame.getInsets();
        int height = 5;

        for (JPanel jp: jPanels) {
            guiFrame.add(jp);
            Dimension size = jp.preferredSize();
            jp.setBounds(220 + insets.left, height + insets.top, size.width, size.height);
            height += 95;
        }

        /*size = panel2.preferredSize();
        panel2.setBounds(220 + insets.left, 100 + insets.top, 20 + size.width, size.height);*/

        guiFrame.setVisible(true);


    }
}