package org.uva.forcepushql.interpreter.TypeChecker;


import org.uva.forcepushql.interpreter.TypeChecker.Helpers.Messages;
import org.uva.forcepushql.parser.ast.elements.FormNode;
import org.uva.forcepushql.parser.ast.elements.Node;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.visitors.BuildASTVisitor;

public class TypeCheck extends BuildASTVisitor implements TypeCheckInterface
{

    private final Messages errors = new Messages();


    private void error(Node node)
    {
        this.errors.addMessage("Incorrect type on line:" + node.getLine() + " starting from character: " + node.getColumn(), Messages.MessageTypes.ERROR);
    }

    public Messages doCheck(FormNode node)
    {
        node.accept((ASTVisitor) this);
        return this.errors;
    }


    public static class Checker implements TypeCheckInterface
    {
        @Override
        public Messages doCheck(FormNode node)
        {
            TypeCheckInterface checker = new TypeCheck();
            return checker.doCheck(node);
        }
    }

    /*TODO: For this to work we need to create node for each value type (e.g. Integer node) and set the expected type
    TODO: of the node there. Then visit and check if the type is the expected one. We should also combine our 2
    TODO: interfaces into 1 interface with Generics (T).*/
}