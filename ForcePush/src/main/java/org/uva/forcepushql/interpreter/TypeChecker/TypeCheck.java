/*package org.uva.forcepushql.interpreter.TypeChecker;


import org.uva.forcepushql.interpreter.TypeChecker.Helpers.Messages;
import org.uva.forcepushql.interpreter.TypeChecker.Helpers.SpecificationKeys;
import org.uva.forcepushql.interpreter.TypeChecker.Helpers.TypesSpecification;
import org.uva.forcepushql.parser.ast.NodeTypes;
import org.uva.forcepushql.parser.ast.visitors.*;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;

import java.util.Map;

public class TypeCheck extends BuildASTVisitor implements TypeCheckInterface {

    private final Messages errors = new Messages();

    private final Map<SpecificationKeys, NodeTypes> typeCheckSpecification;


    private TypeCheck() {
        TypesSpecification typeCheckSpecification = new TypesSpecification();
        this.typeCheckSpecification = typeCheckSpecification.getSpecification();
    }


    private void error(Node node) {
        this.errors.addMessage("Incorrect type on line:" + node.getLine() + " starting from character: " + node.getColumn(), Messages.MessageTypes.ERROR);
    }

    public Messages doCheck(FormNode node) {
        node.accept(this);
        return this.errors;
    }

    private NodeTypes getNodeTypeAndReportErrors(Node node, NodeTypes leftNodeType, NodeTypes rightNodeType) {
        if ((leftNodeType == NodeTypes.INVALID) || (rightNodeType == NodeTypes.INVALID))
            return NodeTypes.INVALID;

        NodeTypes returnType = this.typeCheckSpecification.get(new SpecificationKeys(node.getClass(), leftNodeType, rightNodeType));
        if (returnType == null) {
            this.error(node);
            return NodeTypes.INVALID;
        }
        return returnType;
    }



    public static class Checker implements TypeCheckInterface {
        @Override
        public Messages doCheck(FormNode node) {
            TypeCheckInterface checker = new TypeCheck();
            return checker.doCheck(node);
        }
    }
}*/