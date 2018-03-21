package ql.logic.collectors;

import ql.ast.model.ASTNode;
import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.IfStatement;
import ql.ast.visitors.AbstractASTTraverse;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CollectConditionsVisitor extends AbstractASTTraverse<Void> {

    private List<Expression> conditions = new ArrayList<>();

    public List<Expression> getConditions(@NotNull ASTNode node) {
        conditions = new ArrayList<>();
        node.accept(this);
        return this.conditions;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        this.conditions.add(ifStatement.getCondition());
        return null;
    }
}
