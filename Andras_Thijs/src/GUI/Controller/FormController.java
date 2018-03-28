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

/**
 *  FormController is responsible for updating questions and getting the results when a form is submitted
 */
public class FormController implements RefreshListener {
    private static JFrame frame;
    private final List<QuestionPanel> questionPanels = new ArrayList<>();

    /**
     * Creates a FormController instance
     * @param form The Model containing the parsed questions
     * @param frame The frame that displays the form
     * @throws SyntaxException
     * @throws TypeException
     */
    public FormController(QLForm form, JFrame frame) throws SyntaxException, TypeException {
        FormController.frame = frame;

        List<Question> questions = form.getAllQuestions();
        for (Question question : questions) {
            QuestionPanel questionPanel = new QuestionPanel(question, this);
            questionPanels.add(questionPanel);
        }
    }

    /**
     * Returns the list of QuestionPanels converted from the QLForm's Questions
     * @return The list of QuestionPanels
     */
    public  List<QuestionPanel> getQuestionPanels() {
        return questionPanels;
    }

    /**
     * Refreshes the visibility and values of the QuestionPanels
     * @throws SyntaxException
     * @throws TypeException
     */
    public void refreshQuestions() throws SyntaxException, TypeException {
        for (QuestionPanel questionPanel : questionPanels)
            questionPanel.refreshPanel();
    }

    /**
     * Extracts the results from the form (in JSON format) and closes the window displaying it. The user needs to specify a destination for the results or the methods finishes without changing anything. Unset values are returned as "undefined".
     * @throws SyntaxException
     * @throws TypeException
     */
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
