package gui;

import QL.classes.Question;
import QL.classes.values.Value;
import QL.parsing.visitors.FormVisitor;
import gui.panels.QuestionPanel;
import gui.widgets.WidgetFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class QLBuilder implements Observer {
    private JPanel mainPanel;

    private LinkedHashMap<String, Question> questionHashMap; //collection of questions
    private LinkedHashMap<String, QuestionPanel> questionPanelHashMap; //collection of questionpanels currently active
    private FormVisitor coreVisitor;
    private GridBagConstraints gbc;

    public QLBuilder(FormVisitor coreVisitor) {
        this.coreVisitor = coreVisitor;
        this.questionHashMap = coreVisitor.getQuestions();
        this.questionPanelHashMap = new LinkedHashMap<String, QuestionPanel>();
        this.gbc = initGBC();
        this.mainPanel = new JPanel(new GridBagLayout());
        initQuestionPanels();
    }

    /**
     * initQuestionPanels() method
     * Initialize the creation of the panels containing
     * the question it's controls through iteration
     */
    private void initQuestionPanels() {
        //Iterate over the questions that were passed
        Iterator<Map.Entry<String, Question>> entries = questionHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Question> entry = entries.next();

            //Extract a single question
            Question question = entry.getValue();
            buildQuestionPanel(question);
        }
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    /**
     * buildQuestionPanel() method
     * Build each individual type of question panel and add
     * these to the list panel
     *
     * @param question the question passed
     */
    private void buildQuestionPanel(Question question) {
        Value value = question.getValue();
        QuestionPanel qPanel = new QuestionPanel(question, WidgetFactory.getDefaultWidget(value));

        if (!question.isFixed()) {
            value.addObserver(this);
        }
        if (!question.isVisible()) {
            qPanel.setVisible(false);
        }

        questionPanelHashMap.put(question.getId(), qPanel);
        //add the questionpanel to a map containing active questionpanels
        mainPanel.add(qPanel, this.gbc);
    }

    /**
     * getQuestionContraints() method
     * Receive pre-set question panel constraints
     */
    private GridBagConstraints initGBC() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    /**
     * updateGUI() method
     * Updates the GUI
     */
    private void updateGUI() {
        //Iterate over the total question hashmap
        for (QuestionPanel q : questionPanelHashMap.values()) {
            if (q.getQuestion().isVisible()) {
                q.setVisible(true);
                if (q.getQuestion().isFixed()) {
                    q.refresh();
                }
            } else {
                q.setVisible(false);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.coreVisitor.update();
        this.updateGUI();
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
