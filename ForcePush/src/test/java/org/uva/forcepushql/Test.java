package org.uva.forcepushql;

import org.antlr.v4.runtime.*;
import org.uva.forcepushql.interpreter.gui.JPanelGUI;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.visitors.BuildASTVisitor;
import org.uva.forcepushql.parser.ast.elements.Node;
import org.uva.forcepushql.interpreter.evaluators.ASTVisitorEvaluator;

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


        JFrame guiFrame = new JFrame("Questionnaire");

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setSize(720,720);
        guiFrame.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = GridBagConstraints.NONE;


        //ADD PANELS
        LinkedList<JPanel> jPanels = form.accept(new ASTVisitorEvaluator());

        for (JPanel jp: jPanels) {
            mainPanel.add(jp, gbc);

        }


        guiFrame.add(mainPanel, BorderLayout.CENTER);
        guiFrame.setLocation(300, 300);
        guiFrame.setVisible(true);

    }
}