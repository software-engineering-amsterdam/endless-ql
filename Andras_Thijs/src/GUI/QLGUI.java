package GUI;

import GUI.Controller.FormController;
import GUI.View.QuestionPanel;
import Nodes.QLForm;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * The QLGUI class is responsible for creating the JFrame displaying the QLForm
 */
public class QLGUI {

    private final QLForm form;

    /**
     * Creates an instance of the QLGUI with a given QLForm
     * @param form The QLForm to be displayed
     */
    public QLGUI(QLForm form){
        this.form = form;
    }

    /**
     * Renders the QLForm and creates the FormController responsible for coordinating the various elements
     * @throws SyntaxException
     * @throws TypeException
     */
    public void renderForm() throws SyntaxException, TypeException {

        JFrame frame = new JFrame(form.getName());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        FormController controller = new FormController(form, frame);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for(QuestionPanel questionPanel : controller.getQuestionPanels())
            panel.add(questionPanel);

        JButton button = new JButton("Submit");
        button.addActionListener((ActionEvent e) -> {
            try {
                controller.getResults();
            } catch(TypeException | SyntaxException e1) {
                // This should be impossible, as type checking is done on input;
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

        controller.refreshQuestions();

        frame.setVisible(true);
    }
}
