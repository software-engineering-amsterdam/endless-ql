package ql.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ql.ast.expression.Expression;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.type.Bool;
import ql.gui.fields.actionlisteners.AbstractActionListener;
import ql.helpers.Observer;

public class IfThenElsePanel extends JPanel implements Observer {
    
    private static final long serialVersionUID = -7939006305927112486L;
    private final Expression exp;
    private final ConditionalPanel thenPanel;
    private final ConditionalPanel elsePanel;

    public IfThenElsePanel(Expression exp) {
        super();
        this.exp = exp;
        setName("thenElse");
        setLayout(
                new BoxLayout(this, BoxLayout.PAGE_AXIS));
        thenPanel = new ConditionalPanel("then");
        elsePanel = new ConditionalPanel("else");
        add(thenPanel);
        add(elsePanel);
        update();
    }
    
    @Override
    public void update() {
        boolean render = ((BoolLiteral) new Bool().parse(exp.evaluate())).getValue();
        AbstractActionListener.setVisibility(thenPanel, render);
        AbstractActionListener.setVisibility(elsePanel, !render);
        revalidate();
    }

}
