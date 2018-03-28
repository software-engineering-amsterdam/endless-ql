package GUI.Controller;

import GUI.View.QuestionPanel;
import Main.PathChooser;
import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FormController implements RefreshListener {
    private static JFrame frame;
    private final List<QuestionPanel> questionPanels = new ArrayList<>();

    public FormController(QLForm form, JFrame frame) throws SyntaxException, TypeException {
        FormController.frame = frame;

        List<Question> questions = form.getAllQuestions();
        for (Question question : questions) {
            QuestionPanel questionPanel = new QuestionPanel(question, this);
            questionPanels.add(questionPanel);
        }
    }

    public  List<QuestionPanel> getQuestionPanels() {
        return questionPanels;
    }

    public void refreshQuestions() throws SyntaxException, TypeException {
        for (QuestionPanel questionPanel : questionPanels)
            questionPanel.refreshPanel();
    }

    public void getResults() throws SyntaxException, TypeException {

        StringBuilder results = new StringBuilder("{");

        for (QuestionPanel questionPanel : questionPanels)
            results.append(questionPanel.getResult()).append(",");

        if(results.length() > 1)
            results = new StringBuilder(results.substring(0, results.length() - 1));

        results.append("}");

        try {
            String path = new PathChooser().getDirectoryPath();
            System.out.println(path);
            PrintWriter out = new PrintWriter(new FileWriter(path));
            out.print(results);
            out.close();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
