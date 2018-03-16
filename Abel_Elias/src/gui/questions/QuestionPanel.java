package gui.questions;

import classes.Question;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.EventListener;

public abstract class QuestionPanel extends JPanel {
    private String key;
    private Question question;
    private Boolean isActive;

    public QuestionPanel(String key, Question question) {
        this.key = key;
        this.question = question;
        this.isActive = question.isVisible();
        this.add(new JLabel(question.getText()));
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
    }

    public abstract void createControlWidget(String key);

    public void setPanelState(boolean state) {
        this.isActive = state;
    }

    public Boolean getState() {
        return isActive;
    }

    public Question getQuestion() {
        return question;
    }

    public abstract JComponent getComponent();

    public abstract void setListener(EventListener listener);

//

//    private void addQuestionPanelControls(Question question) {
//        switch(question.getTypeName()) {
//            case String:
//                createStringControl();
//                break;
//            case Boolean:
//                createBoolControl();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void createBoolControl() {
//        JCheckBox checkBox = new JCheckBox();
//        checkBox.addActionListener(new FormBuilder.BoolActionListener(key, checkBox));
//        this.add(checkBox);
//    }
//
//    private void createStringControl() {
//    }
}