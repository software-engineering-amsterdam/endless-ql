package ast.model.statement;

import ast.model.VisitableASTNode;

public interface Statement extends VisitableASTNode {
    public void debugPrint();
}