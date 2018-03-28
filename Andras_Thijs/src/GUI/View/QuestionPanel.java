package GUI.View;
import GUI.Controller.RefreshListener;
import Nodes.Question;
import Nodes.Term.TermFactory;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel{
    private final Question question;
    private boolean isAvailable;
    private Widget widget;

    public QuestionPanel(Question question, RefreshListener listener) throws SyntaxException, TypeException {
        this.question = question;
        this.setName(question.getName());
        this.isAvailable = question.isAvailable();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel label = new JLabel(question.getLabel());
        Widget widget = new WidgetFactory().getWidget(this.question.getType(), listener);
        Component component = widget.getComponent();

        this.setLayout(new GridLayout(1, 2));

        this.add(label);
        this.widget = widget;
        component.setEnabled(!question.hasExpression());
        this.add(component);

    }


    public void refreshPanel() throws SyntaxException, TypeException {
        updateTerm();
        setVisibility();
    }

    private void updateTerm() throws SyntaxException, TypeException {
        if(question.hasExpression()) {
            // Any errors thrown will be handled by Main.
            question.getExpressionValue();

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
            try {
                switch (widget.getType()) {
                    case BOOL:
                        this.question.updateTerm(new TermFactory().getTerm((boolean) this.widget.getValue()));
                        break;
                    case STRING:
                        this.question.updateTerm(new TermFactory().getTerm(this.widget.getValue()));
                        break;
                    case MONEY:
                        this.question.updateTerm(new TermFactory().getTerm((float) this.widget.getValue()));
                        break;
                    case INT:
                        this.question.updateTerm(new TermFactory().getTerm((float) this.widget.getValue()));
                        break;
                    case DECIMAL:
                        this.question.updateTerm(new TermFactory().getTerm((float) this.widget.getValue()));
                        break;
                    case DATE:
                        this.question.updateTerm(new TermFactory().getTerm(this.widget.getValue()));
                        break; //TODO do something with date
                    default:
                        break;
                }
            } catch (Exception e){
                return;
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
            result += (widget.getValue() == null ? "undefined" : widget.getValue()) + "\"";
        else
            result += "undefined" + "\"";
        return result;
    }


}
