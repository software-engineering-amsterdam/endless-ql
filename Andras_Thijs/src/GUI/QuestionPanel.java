package GUI;

import Nodes.Question;

import javax.swing.*;

public class QuestionPanel extends JPanel{
    private Question question;
    private String name;
    private boolean isAvailable;

    public QuestionPanel (Question question){
        this.question = question;
        this.name = question.getName();
        this.isAvailable = question.isAvailable();
        this.add(new JTextField(question.getLabel()));
        this.add(new ComponentFactory().getComponent(this.question.getType()));
    }

    public void setVisibility(){
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }
}
