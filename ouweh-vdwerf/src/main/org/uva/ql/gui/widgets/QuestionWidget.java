package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;

import javax.swing.*;
import java.awt.*;

public abstract class QuestionWidget extends JPanel {

    private final String id;

    public QuestionWidget(Question question) {
        this.id = question.getName();

        this.setLayout(new GridLayout(1, 2));

        JLabel questionLabel = new JLabel(question.getContent());
        questionLabel.setVisible(true);
        this.add(questionLabel, 0);

        this.setVisible(true);
    }
}
