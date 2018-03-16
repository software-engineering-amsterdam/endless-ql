package ql.gui;

import ql.ast.expression.Expression;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.type.Bool;
import ql.gui.fields.actionlisteners.AbstractActionListener;
import ql.helpers.Observer;

public class IfThenPanel extends ConditionalPanel implements Observer {

    private static final long serialVersionUID = -2718716182504678952L;
    private final Expression exp;
    
    public IfThenPanel(Expression exp, String name) {
        super(name);
        this.exp = exp;
        update();
    }
    
    @Override
    public void update() {
        boolean render = ((BoolLiteral) new Bool().parse(exp.evaluate())).getValue();
        AbstractActionListener.setVisibility(this, render);
        revalidate();
    }

}
