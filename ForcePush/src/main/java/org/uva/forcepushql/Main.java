package org.uva.forcepushql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.forcepushql.interpreter.evaluators.ASTVisitorEvaluator;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.elements.Node;
import org.uva.forcepushql.parser.ast.visitors.BuildASTVisitor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws Exception{
           run("src/main/resources/antlr/TestInput.txt");
    }

    public static void run(String file) throws IOException {
        File testFile = new File(file);
        InputStream stream = new FileInputStream(testFile);
        ANTLRInputStream input = new ANTLRInputStream(stream);
        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        Node compileUnit = new BuildASTVisitor().visitCompileUnit(parser.compileUnit());

        JFrame guiFrame = new JFrame("Questionnaire");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setSize(600,250);
        guiFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = GridBagConstraints.NONE;

        JScrollPane jsp = new JScrollPane(mainPanel);

        jsp.setPreferredSize(new Dimension(300,300));
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        LinkedList<JPanel> jPanels = compileUnit.accept(new ASTVisitorEvaluator());

        for (JPanel jp: jPanels) {
            mainPanel.add(jp, gbc);
        }

        guiFrame.add(jsp, BorderLayout.CENTER);
        guiFrame.setLocation(450, 100);
        guiFrame.setVisible(true);

    }
}
