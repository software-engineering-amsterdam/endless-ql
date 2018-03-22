package GUI;

import GUI.Listeners.RefreshListener;
import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

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
        FormLayout layout = new FormLayout(
                "left:default, 3dlu default");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout);

        List<Question> questions = form.getAllQuestions();
        questions.stream().map(question -> new QuestionPanel(question, this)).forEach(questionPanel -> {
            questionPanels.add(questionPanel);
            builder.append(questionPanel);
        });

        JPanel panel = builder.getPanel();

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
