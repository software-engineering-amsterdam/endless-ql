package org.uva.forcepushql.ast;

abstract class ASTVisitor<T> extends  BuildASTVisitor{

    public T Visit(BuildASTVisitor node)
    {
        if(node is PlusExpression)
        {
            return visitExpression(PlusExpression.node);
        }
        else if(node is MinusExpression)
        {
            return Visit((MinusExpression) node);
        }
    }
}