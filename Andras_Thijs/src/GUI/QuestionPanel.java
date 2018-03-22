package GUI;
import GUI.Listeners.RefreshListener;
import Nodes.Question;
import Nodes.Term.TermFactory;
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


    public void refreshPanel() {
        updateTerm();
        setVisibility();
    }

    private void updateTerm() {
        switch (widget.getType()){
            case BOOL: this.question.updateTerm(new TermFactory().getTerm((boolean)this.widget.getValue()));break;
            case STRING: this.question.updateTerm(new TermFactory().getTerm(this.widget.getValue()));break;
            case MONEY: this.question.updateTerm(new TermFactory().getTerm((float)this.widget.getValue()));break;
            case INT: this.question.updateTerm(new TermFactory().getTerm((float)this.widget.getValue()));break;
            case DECIMAL: this.question.updateTerm(new TermFactory().getTerm((float)this.widget.getValue()));break;
            case DATE: this.question.updateTerm(new TermFactory().getTerm(this.widget.getValue()));break; //TODO do something with date
            default: break;
        }

    }

    private void setVisibility(){
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }


}
