package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.operator.BinaryOperator;
import com.chariotit.uva.sc.qdsl.ast.ql.node.operator.Operator;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.Set;

public class LabelBinOpExpression extends Expression {

    private LabelExpression labelExpression;
    private Operator operator;
    private Expression expression;

    public LabelBinOpExpression(LabelExpression labelExpression,
                                Operator operator,
                                Expression expression,
                                SourceFilePosition filePosition) {
        super(filePosition);
        this.labelExpression = labelExpression;
        this.operator = operator;
        this.expression = expression;
    }

    public LabelExpression getLabelExpression() {
        return labelExpression;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void evaluate(SymbolTable symbolTable) {
        labelExpression.evaluate(symbolTable);
        expression.evaluate(symbolTable);

        setExpressionValue(((BinaryOperator)operator).evaluate(symbolTable, labelExpression,
                expression));
    }

    @Override
    public Set<String> getPrerequisites() {
        Set<String> set1 = expression.getPrerequisites();
        Set<String> set2 = labelExpression.getPrerequisites();
        set1.addAll(set2);
        return set1;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        labelExpression.acceptVisitor(visitor);
        operator.acceptVisitor(visitor);
        expression.acceptVisitor(visitor);

        visitor.visitLabelBinOpExpression(this);
    }
}
