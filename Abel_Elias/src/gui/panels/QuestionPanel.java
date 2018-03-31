package gui.panels;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.listeners.QuestionListener;
import gui.widgets.Widget;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.EventListener;

public class QuestionPanel extends JPanel {
    private Question question;
    private Boolean isActive;
    private Widget widget;

    public QuestionPanel(Question question, Widget widget) {
        this.question = question;
        this.widget = widget;
        this.isActive = question.isVisible();

        widget.getJComponent().setEnabled(!question.isFixed());

        this.add(new JLabel(question.getText()));
        this.add(widget.getJComponent());

        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
    }

    public Question getQuestion() {
        return question;
    }

    public void refresh(){
        widget.refresh();
    }

}