package gui.controller;

import ast.ASTBuilder;
import ast.model.Form;
import ast.model.expressions.Expression;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import com.google.gson.Gson;
import grammar.QLLexer;
import grammar.QLParser;
import gui.model.QuestionModel;
import gui.view.QuestionPanel;
import logic.collectors.CollectConditionsVisitor;
import logic.collectors.CollectQuestionModelsVisitor;
import logic.collectors.CollectQuestionsVisitor;
import logic.collectors.CollectReferencesVisitor;
import logic.evaluators.FormModelExpressionEvaluator;
import logic.validators.*;
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

public class FileOpenActionEvent implements ActionListener {

    private JFrame frame;

    public FileOpenActionEvent(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();

            CharStream charStream = null;
            try {
                charStream = CharStreams.fromFileName(selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Lexer
            QLLexer qlLexer = new QLLexer(charStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);

            // Parser
            QLParser qlParser = new QLParser(commonTokenStream);
            QLParser.FormContext formContext = qlParser.form();

            // AST Builder
            ASTBuilder astBuilder = new ASTBuilder();
            Form form = astBuilder.visitForm(formContext);

            // Collecting data

            // Collect all references from all expressions in the form (both: assignments and conditions)
            CollectReferencesVisitor collectReferencesVisitor = new CollectReferencesVisitor();
            List<VariableReference> references = collectReferencesVisitor.getVariableReferences(form);

            // Collect all questions
            CollectQuestionsVisitor collectQuestionsVisitor = new CollectQuestionsVisitor();
            List<Question> questions = collectQuestionsVisitor.getQuestions(form);

            // Collect all conditions
            CollectConditionsVisitor collectConditionsVisitor = new CollectConditionsVisitor();
            List<Expression> conditions = collectConditionsVisitor.getConditions(form);

            // Collect questions with their references for cyclic dependency validator
            HashMap<Question, List<VariableReference>> questionsMap = collectQuestionsVisitor.getQuestionsMap(form);

            // Validators

            Boolean validated = true;

            // Validate undeclared variables usage in questions and conditions
            try {
                VariablesReferencesValidator.validateVariablesUsage(questions, references);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                validated = false;
            }

            // Validate duplicate question declarations with different types
            try {
                QuestionsValidator.validateDuplicates(questions);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                validated = false;
            }

            // Validate conditions that are not of the type boolean
            try {
                ConditionsValidator.validateConditions(conditions, questions);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                validated = false;
            }

            // Validate cyclic dependencies in questions and expressions
            try {
                ConditionsValidator.validateConditions(conditions, questions);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                validated = false;
            }

            // Validate operands of invalid type to operators
            try {
                TypesValidator.validateTypes(conditions, questions);
                QuestionsDependencyValidator questionsDependencyValidator = new QuestionsDependencyValidator(questionsMap);

                System.out.println("uuu");

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                validated = false;
            }

            // Validate duplicate labels (warning)
            try {
                QuestionsValidator.validateLabels(questions);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            }

            if (validated) {

                // hide the initial frame
                this.frame.setVisible(false);

                // proceed with actual form modelling and rendering

                // start: ONE LIST TO RULE THEM ALL
                CollectQuestionModelsVisitor collectQuestionModelsVisitor = new CollectQuestionModelsVisitor();
                List<QuestionModel> questionModels = collectQuestionModelsVisitor.getQuestionModels(form);
                // end: ONE LIST TO RULE THEM ALL

                FormModelExpressionEvaluator evaluator = new FormModelExpressionEvaluator(questionModels);

                // GUI

                JPanel panel = new JPanel(new GridBagLayout());

                TitledBorder titled = new TitledBorder(form.getName());
                panel.setBorder(titled);

                JScrollPane scrollFrame = new JScrollPane(panel);
                panel.setAutoscrolls(true);
                scrollFrame.setPreferredSize(new Dimension(600, 800));

                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.anchor = GridBagConstraints.WEST;

                // form controller setup
                FormController formController = new FormController(questionModels, evaluator);

                // render form questions panels
                int i = 0;
                for (QuestionModel questionModel : questionModels) {
                    gridBagConstraints.gridy = i;
                    panel.add(new QuestionPanel(questionModel), gridBagConstraints);
                    i++;
                }

                // Form submit
                JButton submit = new JButton("Submit form");
                submit.addActionListener(submitEvent -> {

                    Gson gson = new Gson();
                    String jsonResults = gson.toJson(formController.prepareResults());

                    fileChooser.setSelectedFile(new File(form.getName() + "-result.json"));
                    int returnVal = fileChooser.showSaveDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write(jsonResults);
                            writer.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                gridBagConstraints.gridy = i;
                panel.add(submit, gridBagConstraints);

                // frame rendering
                this.frame.getContentPane().removeAll();
                this.frame.getContentPane().add(scrollFrame);
                this.frame.pack();
                this.frame.setLocationRelativeTo(null);
                this.frame.setVisible(true);
                this.frame.setResizable(false);
            }
        }
    }
}
