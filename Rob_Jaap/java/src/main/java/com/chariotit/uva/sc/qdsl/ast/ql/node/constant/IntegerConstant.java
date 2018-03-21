package com.chariotit.uva.sc.qdsl.ast.ql.node.constant;

import com.chariotit.uva.sc.qdsl.ast.IntegerExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class IntegerConstant extends Constant {

    private IntegerExpressionValue value;

    public IntegerConstant(IntegerExpressionValue value, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    public IntegerExpressionValue getValue() {
        return value;
    }

    public void setValue(IntegerExpressionValue value) {
        this.value = value;
    }

    @Override
    public void evaluate(SymbolTable symbolTable) {

    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitIntegerConstant(this);
    }
}
