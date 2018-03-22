package GUI;
import GUI.Listeners.RefreshListener;
import Nodes.Question;
import Nodes.Term.TermFactory;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;
import javax.swing.*;

public class QuestionPanel extends JPanel{
    private Question question;
    private boolean isAvailable;
    private Widget widget;

    QuestionPanel(Question question, RefreshListener listener){
        this.question = question;
        this.setName(question.getName());
        this.isAvailable = question.isAvailable();
        this.add(new JLabel(question.getLabel()));
        this.widget = new WidgetFactory().getWidget(this.question.getType(), listener);
        this.add(widget.getComponent());

    }


    public void refreshPanel() throws SyntaxException, TypeException {
        updateTerm();
        setVisibility();
    }

    private void updateTerm() throws SyntaxException, TypeException {
        switch (widget.getType()){
            case BOOL: this.question.updateTerm(new TermFactory().getTerm((boolean)this.widget.getValue()));break;
            case STRING: this.question.updateTerm(new TermFactory().getTerm(this.widget.getValue()));break;
            default: this.question.updateTerm(new TermFactory().getTerm((float)this.widget.getValue()));break;
        }

    }

    private void setVisibility(){
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }


}
