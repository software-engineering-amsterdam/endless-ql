package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.expression.literal.Literal;
import ql.gui.alternative.ASTtoGUI;
import ql.helpers.Observable;
import ql.helpers.Observer;
import ql.visitors.interfaces.StatementVisitor;

public class ComputedQuestion extends Question implements Observer {
    
    private Expression computation;

    public ComputedQuestion(String label, Identifier id, Expression expr) {
        super(label, id);
        this.computation = expr;
    }

    public Expression getComputation() {
        return computation;
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id + ": " + id.getType() + "( " + computation.toString() + " )";
    }
    
    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public void update(Observable observable, Literal<?>[] value) {
        id.setValue(computation.evaluate());
        this.accept(new ASTtoGUI());
    }
}
