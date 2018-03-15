package com.chariotit.uva.sc.qdsl.qls.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class DefaultProperties extends AstNode {

    private ExpressionType expressionType;
    private Properties properties;

    public DefaultProperties(ExpressionType expressionType, Properties properties, Integer
            lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.expressionType = expressionType;
        this.properties = properties;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
