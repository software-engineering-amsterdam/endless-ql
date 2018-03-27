package GUI;

import GUI.Controller.RefreshListener;
import GUI.View.QuestionPanel;
import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class QLGUI implements RefreshListener{

    private QLForm form;
    private List<QuestionPanel> questionPanels;

    public QLGUI(QLForm form){
        this.form = form;
        questionPanels = new ArrayList<>();
    }

    public void renderForm() throws SyntaxException, TypeException {

        JFrame frame = new JFrame(form.getName());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        //panel.setPreferredSize(new Dimension(400, 600));

        List<Question> questions = form.getAllQuestions();
        for (Question question : questions) {
            QuestionPanel questionPanel = new QuestionPanel(question, this);
            questionPanels.add(questionPanel);
            panel.add(questionPanel);
        }

        JButton button = new JButton("Submit");

        button.addActionListener(e -> {
            try {
                System.out.println(getResults());
            } catch (SyntaxException e1) {
                e1.printStackTrace();
            } catch (TypeException e1) {
                e1.printStackTrace();
            }
        });

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(button);

        panel.add(buttonPanel);


        panel.setMaximumSize(new Dimension(400, 600));

        JScrollPane scrPane = new JScrollPane(panel);

        scrPane.setMaximumSize(new Dimension(400, 600));


        frame.getContentPane().add(scrPane);

        frame.setResizable(false);

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
