package com.chariotit.uva.sc.qdsl.ast.ql.node.constant;

import com.chariotit.uva.sc.qdsl.ast.StringExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class StringConstant extends Constant {

    private StringExpressionValue value;

    public StringConstant(StringExpressionValue value, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    public StringExpressionValue getValue() {
        return value;
    }

    public void setValue(StringExpressionValue value) {
        this.value = value;
    }

    @Override
    public void evaluate(SymbolTable symbolTable) {

    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringConstant(this);
    }
}
