package GUI;

import GUI.Listeners.RefreshListener;
import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;
import javax.swing.*;
import java.util.*;
import java.util.List;

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
        questions.stream().map(question -> new QuestionPanel(question, this)).forEach(questionPanel -> {
            questionPanels.add(questionPanel);
            panel.add(questionPanel);
        });

        frame.add(panel);

        frame.pack();

        refreshQuestions();

        frame.setVisible(true);

    }

    private void refreshView() throws SyntaxException, TypeException {
        for (QuestionPanel questionPanel : questionPanels) {
            questionPanel.refreshPanel();
        }
    }

    @Override
    public void refreshQuestions() throws SyntaxException, TypeException {
        refreshView();
    }
}
