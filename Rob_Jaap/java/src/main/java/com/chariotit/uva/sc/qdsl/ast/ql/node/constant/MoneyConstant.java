package com.chariotit.uva.sc.qdsl.ast.ql.node.constant;

import com.chariotit.uva.sc.qdsl.ast.MoneyExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class MoneyConstant extends Constant {

    private MoneyExpressionValue value;

    public MoneyConstant(MoneyExpressionValue value, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    public MoneyExpressionValue getValue() {
        return value;
    }

    public void setValue(MoneyExpressionValue value) {
        this.value = value;
    }

    @Override
    public void evaluate(SymbolTable symbolTable) {

    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMoneyConstant(this);
    }
}
