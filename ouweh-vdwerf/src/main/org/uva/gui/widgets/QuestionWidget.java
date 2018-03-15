package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;

import javax.swing.*;
import java.awt.*;

public abstract class QuestionWidget extends JPanel {

    protected final Question question;

    public QuestionWidget(Question question) {
        this.question = question;

        this.setLayout(new GridLayout(1, 2));
        JLabel questionLabel = new JLabel(question.getContent());
        questionLabel.setVisible(true);
        this.add(questionLabel, 0);

        this.setVisible(true);
    }

    public abstract void setQuestionChangeListener(QuestionChangeListener questionChangeListener);
}
