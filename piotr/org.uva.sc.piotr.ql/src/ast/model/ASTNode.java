package ast.model;

import com.sun.istack.internal.NotNull;

abstract public class ASTNode implements VisitableASTNode {

    private Integer startLine;
    private Integer endLine;

    public ASTNode(Integer startLine, Integer endLine) {
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }
}
