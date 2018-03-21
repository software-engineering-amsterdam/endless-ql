package ql.gui.controller;

import com.google.gson.Gson;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.ast.ASTBuilder;
import ql.ast.model.Form;
import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.gui.model.QuestionModel;
import ql.gui.view.QuestionPanel;
import ql.logic.collectors.CollectConditionsVisitor;
import ql.logic.collectors.CollectQuestionModelsVisitor;
import ql.logic.collectors.CollectQuestionsVisitor;
import ql.logic.collectors.CollectReferencesVisitor;
import ql.logic.evaluators.FormModelExpressionEvaluator;
import ql.logic.validators.*;

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

            Validator[] validators = new Validator[]{
                    new VariablesReferencesValidator(questions, references),
                    new QuestionsDuplicationValidator(questions),
                    new ConditionsValidator(conditions, questions),
                    new TypesValidator(conditions, questions),
                    new QuestionsDependencyValidator(questionsMap),
                    new QuestionLabelsValidator(questions)
            };

            for (Validator validator : validators) {
                if (!validator.validate()) {
                    if (validator.getErrorLevel() == Error.Level.CRITICAL) {
                        JOptionPane.showMessageDialog(frame, validator.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        validated = false;
                    } else {
                        JOptionPane.showMessageDialog(frame, validator.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
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
