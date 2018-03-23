package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class DefaultProperties extends AstNode {

    private ExpressionType expressionType;
    private Properties properties;

    public DefaultProperties(ExpressionType expressionType, Properties properties, SourceFilePosition
            filePosition) {
        super(filePosition);
        this.expressionType = expressionType;
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        properties.acceptVisitor(visitor);

        visitor.visitDefaultProperties(this);
    }
}
