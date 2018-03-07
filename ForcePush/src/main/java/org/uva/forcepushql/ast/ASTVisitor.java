package org.uva.forcepushql.ast;

abstract class ASTVisitor<T>{

    public T Visit(BinaryExpression node)
    {
        if(node instanceof AdditionNode)
        {
            return Visit((AdditionNode)node);
        }
        else if(node instanceof SubtractionNode)
        {
            return Visit((SubtractionNode) node);
        }
        else if(node instanceof DivisionNode)
        {
            return Visit((DivisionNode) node);
        }
        else if(node instanceof MultiplicationNode)
        {
            return Visit((MultiplicationNode) node);
        }
        else{
            return null; //REPLACE WITH ERROR!
        }
    }
}