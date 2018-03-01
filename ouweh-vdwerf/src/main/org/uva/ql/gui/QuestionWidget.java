package org.uva.ql.gui;

import org.uva.ql.ast.Question;
import javax.swing.*;

public class QuestionWidget extends JPanel{

    public QuestionWidget(Question question) {

        JLabel questionLabel = new JLabel();
        questionLabel.setText(question.getContent());
        questionLabel.setVisible(true);
        this.add(questionLabel);

        switch (question.getType().toString()){
            case "BooleanType":
                JCheckBox checkBox = new JCheckBox();
                this.add(checkBox);
                break;
            default:
                JTextField textField = new JTextField();
                this.add(textField);
        }
        this.setVisible(true);
    }
}
