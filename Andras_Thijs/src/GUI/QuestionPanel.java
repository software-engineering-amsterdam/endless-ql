package GUI;

import GUI.Listeners.RefreshListener;
import Nodes.Question;
import Nodes.Term.TermFactory;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel{
    private Question question;
    private boolean isAvailable;
    private Widget widget;

    public QuestionPanel (Question question, RefreshListener listener){
        this.question = question;
        this.setName(question.getName());
        this.isAvailable = question.isAvailable();
        this.add(new JLabel(question.getLabel()));
        this.widget = new WidgetFactory().getWidget(this.question.getType(), listener);
        this.add(widget.getComponent());

    }


    public void updateTerm() throws SyntaxException, TypeException {
        this.question.updateTerm(new TermFactory().getTerm(this.widget.getValue()));
    }

    public void setVisibility(){
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }


}
