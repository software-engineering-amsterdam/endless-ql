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

    QuestionPanel(Question question, RefreshListener listener) throws SyntaxException, TypeException {
        this.question = question;
        this.setName(question.getName());
        this.isAvailable = question.isAvailable();
        this.add(new JLabel(question.getLabel()), BorderLayout.WEST);
        this.widget = new WidgetFactory().getWidget(this.question.getType(), listener);
        this.widget.getComponent().setEnabled(!question.hasExpression());
        this.add(widget.getComponent(), BorderLayout.EAST);

    }


    public void refreshPanel() throws SyntaxException, TypeException {
        updateTerm();
        setVisibility();
    }

    private void updateTerm() throws TypeException {
        if(question.hasExpression()) {
            try {
                question.getExpressionValue();
            } catch (TypeException e) {
                e.printStackTrace();
            } catch (SyntaxException e) {
                e.printStackTrace();
            }
            if(question.getResult() == null)
                return;
            switch (widget.getType()){
                case BOOL: ((JCheckBox)widget.getComponent()).setSelected(question.getResult().getBoolean());break;
                case STRING: ((JTextField)widget.getComponent()).setText(question.getResult().getString());break;
                case MONEY: ((JTextField)widget.getComponent()).setText(String.valueOf(question.getResult().getFloat()));break;
                case INT: ((JTextField)widget.getComponent()).setText(String.valueOf(question.getResult().getFloat()));break;
                case DECIMAL: ((JTextField)widget.getComponent()).setText(String.valueOf(question.getResult().getFloat()));break;
                case DATE: ((JTextField)widget.getComponent()).setText(question.getResult().getString());break; //TODO do something with date
                default: break;
            }
        } else {
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

    }

    private void setVisibility() throws SyntaxException, TypeException {
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }

    public String getResult() throws SyntaxException, TypeException {
        String result = "\"" + question.getName() + "\"" + ":\"";
        if(question.isAvailable())
            result += widget.getValue() + "\"";
        else
            result += "undefined" + "\"";
        return result;
    }


}
