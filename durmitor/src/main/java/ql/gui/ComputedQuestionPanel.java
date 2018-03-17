package ql.gui;

import ql.ast.statement.ComputedQuestion;
import ql.gui.fields.actionlisteners.AbstractActionListener;
import ql.helpers.Observer;
import ql.visitors.checker.checkers.ExpressionVisitorIdentifier;

public class ComputedQuestionPanel extends QuestionPanel implements Observer {

    private static final long serialVersionUID = -4228538879739758745L;

    public ComputedQuestionPanel(ComputedQuestion stmt) {
        super(stmt);
        setName("computedQuestion");
        stmt.getComputation().accept(new ExpressionVisitorIdentifier()).forEach(identifier -> {
            identifier.addObserver(stmt.getIdentifier());
        });
        update();
    }

    @Override
    public void update() {
        System.out.println(this.getQuestion().getIdentifier().getValue().evaluate());
        AbstractActionListener.setVisibility(this, true);
        revalidate();
    }

}
