package gui.panels;

import QL.classes.Question;
import gui.widgets.Widget;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

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

    public void replaceWidget(Widget newWidget){
        this.widget = newWidget;

        this.removeAll();
        this.add(new JLabel(question.getText()));
        this.add(newWidget.getJComponent());
    }

    public Question getQuestion() {
        return question;
    }

    public void refresh(){
        widget.refresh();
        this.setVisible(question.isVisible());
    }
}