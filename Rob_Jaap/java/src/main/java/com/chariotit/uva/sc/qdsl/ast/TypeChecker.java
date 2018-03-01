package com.chariotit.uva.sc.qdsl.ast;


import com.chariotit.uva.sc.qdsl.ast.node.AstRoot;
import com.chariotit.uva.sc.qdsl.ast.visitor.TypeCheckVisitor;

/**
 * Should check:
 *
 * reference to undefined questions
 * duplicate question declarations with different types
 * conditions that are not of the type boolean
 * operands of invalid type to operators
 * cyclic dependencies between questions
 * duplicate labels (warning)
 */
public class TypeChecker {

    public void typeCheckAst(AstRoot astRoot) {
        System.out.println("HERE");
        TypeCheckVisitor visitor = new TypeCheckVisitor();

        astRoot.acceptVisitor(visitor);

    }
}
