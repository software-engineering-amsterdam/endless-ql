package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.interpreter.gui.JPanelGUI;

import java.util.LinkedList;

public class ConditionalIfNode extends ConditionalNode
{
    @Override
    public LinkedList<JPanelGUI> accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }
}
