package gui;

import ast.ASTBuilder;
import ast.model.Form;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import com.google.gson.Gson;
import grammar.QLLexer;
import grammar.QLParser;
import gui.controller.FileOpenActionEvent;
import gui.controller.FormController;
import gui.model.QuestionModel;
import gui.view.QuestionPanel;
import logic.collectors.CollectQuestionModelsVisitor;
import logic.collectors.CollectQuestionsVisitor;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.ExpressionEvaluator;
import logic.validators.QuestionsDependencyValidator;
import logic.validators.QuestionsValidator;
import logic.validators.VariablesReferencesValidator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class QLGui {
    public QLGui() throws Exception {

        JFrame frame = new JFrame("QL Form GUI");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton openingButton = new JButton("Open file...");
        JPanel openingPanel = new JPanel();
        openingPanel.setPreferredSize(new Dimension(200, 200));

        openingPanel.add(openingButton);
        frame.getContentPane().add(openingPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        openingButton.addActionListener(new FileOpenActionEvent(frame));

    }

}
