package qls.ast.model;

abstract public class ASTNode implements VisitableASTNode {

    private MetaInformation metaInformation;

    protected ASTNode(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }

    protected ASTNode() {
    }

    public MetaInformation getMetaInformation() {
        if (metaInformation == null)
            return new MetaInformation(0);
        return metaInformation;
    }

    public void setMetaInformation(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }

    public static class MetaInformation {

        private final Integer startLine;

        public MetaInformation(Integer startLine) {
            this.startLine = startLine;
        }

        public Integer getStartLine() {
            return startLine;
        }

    }
}
