package ql.logic.collectors;

import com.sun.istack.internal.NotNull;
import ql.ast.model.ASTNode;
import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.IfStatement;
import ql.ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;
import java.util.List;

public class CollectConditionsVisitor extends AbstractASTTraverse<Void> {

    private List<Expression> conditions = new ArrayList<>();

    public List<Expression> getConditions(ASTNode node) {
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
