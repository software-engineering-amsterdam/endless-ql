package ql.gui;

import ql.ast.expression.Identifier;
import ql.ast.statement.ComputedQuestion;
import ql.helpers.Observer;
import ql.visitors.checker.checkers.ExpressionVisitorIdentifier;

public class ComputedQuestionPanel extends QuestionPanel implements Observer {

    private static final long serialVersionUID = -4228538879739758745L;

    public ComputedQuestionPanel(ComputedQuestion question) {
        super(question);
        setName("computedQuestion");
        setVisible(true);
        question.getComputation().accept(new ExpressionVisitorIdentifier()).forEach(identifier -> {
            identifier.addObserver(question);
        });
        question.getIdentifier().addObserver(this);
        update();
    }

    @Override
    public void update() {
        Identifier identifier = getQuestion().getIdentifier();
        
        System.out.println(identifier.getValue());
        
        revalidate();
    }

}
