package GUI;

import GUI.Listeners.RefreshListener;
import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FormTemplate implements RefreshListener{

    private QLForm form;
    private List<QuestionPanel> questionPanels;

    public FormTemplate(QLForm form){
        this.form = form;
        questionPanels = new ArrayList<>();
    }

    public void renderForm() throws SyntaxException, TypeException {

        JFrame frame = new JFrame(form.getName());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        List<Question> questions = form.getAllQuestions();
        for (Question question : questions) {
            QuestionPanel questionPanel = new QuestionPanel(question, this);
            questionPanels.add(questionPanel);
            panel.add(questionPanel);
        }

        JButton button = new JButton("Submit");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(getResults());
                } catch (SyntaxException e1) {
                    e1.printStackTrace();
                } catch (TypeException e1) {
                    e1.printStackTrace();
                }
            }
        });


        panel.add(button);

        frame.add(panel);

        frame.pack();

        refreshQuestions();

        frame.setVisible(true);

    }


    @Override
    public void refreshQuestions() throws SyntaxException, TypeException {
        for (QuestionPanel questionPanel : questionPanels) {
            questionPanel.refreshPanel();
        }
    }

    public String getResults() throws SyntaxException, TypeException {
        String results = "{";
        for (QuestionPanel questionPanel : questionPanels) {
            results += questionPanel.getResult() + ",";
        }
        if(results.length() > 1)
            results = results.substring(0, results.length() - 1);
        results += "}";
        return results;
    }
}
