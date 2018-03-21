package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;

public abstract class QuestionWidget extends JPanel {

    protected final Question question;
    protected JLabel questionLabel;

    public QuestionWidget(Question question) {
        this.question = question;

        this.setLayout(new GridLayout(1, 2));
        questionLabel = new JLabel(question.getContent());
        questionLabel.setVisible(true);
        this.add(questionLabel, 0);
        this.setVisible(true);
    }

    public void setFont(Font font) {

    }

    public void setFontSize(int fontSize) {

    }

    public void setWidth(int width) {

    }

    public void setColor(Color color) {
        this.setBackground(color);
    }



    public abstract void setQuestionChangeListener(QuestionChangeListener questionChangeListener);
}
