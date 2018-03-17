package GUI;

import GUI.Listeners.RefreshListener;
import Nodes.Question;
import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel{
    private Question question;
    private boolean isAvailable;

    public QuestionPanel (Question question, RefreshListener listener){
        this.question = question;
        this.setName(question.getName());
        this.isAvailable = question.isAvailable();
        this.add(new JLabel(question.getLabel()));
        Component component = new ComponentFactory().getComponent(this.question.getType(), listener);
        this.add(component);

    }

    public void setVisibility(){
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }
}
