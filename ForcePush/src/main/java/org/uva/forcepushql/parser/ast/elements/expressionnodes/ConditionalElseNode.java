package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.interpreter.gui.JPanelGUI;

public class ConditionalElseNode extends ConditionalNode
{
    @Override
    public JPanelGUI accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }
}
