package ql.logic.collectors;

import ql.ast.model.ASTNode;
import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.values.Literal;
import ql.ast.visitors.AbstractASTTraverse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CollectDateLiteralsVisitor extends AbstractASTTraverse<Void> {

    private List<Literal> literals = new ArrayList<>();

    public List<Literal> getLiterals(ASTNode node) {
        literals = new ArrayList<>();
        node.accept(this);
        return this.literals;
    }

    @Override
    public Void visit(Literal literal) {
        if (literal.getType().equals(Expression.DataType.DATE))
            this.literals.add(literal);
        return null;
    }
}
