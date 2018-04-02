package GUI.View;
import GUI.Controller.RefreshListener;
import Nodes.Question;
import Nodes.Term.TermFactory;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import javax.swing.*;
import java.awt.*;

/**
 * The QuestionPanel class contains a QL Question and a Widget as a JPanel
 */

public class QuestionPanel extends JPanel{
    private final Question question;
    private boolean isAvailable;
    private Widget widget;


    /**
     *
     * @param question  The QL Questioned housed by the panel
     * @param listener  The listener that will be passed down to the Widget (normally this is the QLGUI containing the panel)
     * @throws SyntaxException
     * @throws TypeException
     */
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

    /**
     * Makes the panel visible if all the necessary conditions are met and adjust the value if the Question has an expression
     * @throws SyntaxException
     * @throws TypeException
     */
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
            switch(widget.getType()){
                case BOOL: ((JCheckBox) widget.getComponent()).setSelected(question.getResult().getBoolean()); break;
                case STRING: ((JTextField) widget.getComponent()).setText(question.getResult().getString()); break;
                case MONEY: ((JTextField) widget.getComponent()).setText(String.valueOf(question.getResult().getFloat())); break;
                case INT: ((JTextField) widget.getComponent()).setText(String.valueOf(question.getResult().getFloat())); break;
                case DECIMAL: ((JTextField) widget.getComponent()).setText(String.valueOf(question.getResult().getFloat())); break;
                case DATE: ((JTextField) widget.getComponent()).setText(question.getResult().getString()); break;
                default: break;
            }
        } else {

            if(widget.getValue() == null)
                return;

            switch(widget.getType()) {
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
                    break;
                default:
                    break;
            }
        }
    }

    private void setVisibility() throws SyntaxException, TypeException {
        this.isAvailable = question.isAvailable();
        this.setVisible(this.isAvailable);
    }

    /**
     * Returns the value of the Question
     * @return Returns either the value if set, or "undefined" otherwise
     * @throws SyntaxException
     * @throws TypeException
     */
    public String getResult() throws SyntaxException, TypeException {
        String result = "\"" + question.getName() + "\"" + ":\"";
        if(question.isAvailable())
            result += (widget.getValue() == null ? "undefined" : widget.getValue()) + "\"";
        else
            result += "undefined" + "\"";
        return result;
    }
}
