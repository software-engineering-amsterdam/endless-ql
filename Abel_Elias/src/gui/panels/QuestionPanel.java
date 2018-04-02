package gui.panels;

import QL.classes.Question;
import gui.widgets.Widget;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class QuestionPanel extends JPanel {
    final JLabel questionLabel;
    private Question question;
    private Boolean isActive;
    private Widget widget;

    public QuestionPanel(Question question, Widget widget) {
        this.question = question;
        this.widget = widget;
        this.isActive = question.isVisible();

        widget.getJComponent().setEnabled(!question.isFixed());

        this.questionLabel = new JLabel(question.getText());
        this.add(questionLabel);
        this.add(widget.getJComponent());

        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
    }

    public Question getQuestion() {
        return question;
    }


    public void setWidth(int width) {
        this.getLayout().preferredLayoutSize(this).width = width;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    public void setFont(String font) {
        Font newFont = new Font(font, Font.PLAIN, questionLabel.getFont().getSize());
        this.questionLabel.setFont(newFont);
    }

    public void setFontSize(int fontSize) {
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.questionLabel.setFont(newFont);
    }

    public void refresh() {
        widget.refresh();
        this.setVisible(question.isVisible());
    }
}