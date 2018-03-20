package ast.model;

abstract public class ASTNode implements VisitableASTNode {

    public static class MetaInformation {

        private final Integer startLine;
        private final Integer endLine;
        private final String text;        //TODO: YAGNI?

        public MetaInformation(Integer startLine, Integer endLine, String text) {
            this.startLine = startLine;
            this.endLine = endLine;
            this.text = text;
        }

        public Integer getStartLine() {
            return startLine;
        }

        public Integer getEndLine() {
            return endLine;
        }

        public String getText() {
            return text;
        }

    }

    private MetaInformation metaInformation;

    protected ASTNode(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }

    protected ASTNode() { }

    public MetaInformation getMetaInformation() {
        if (metaInformation == null)
            return new MetaInformation(0,0, "<< no meta information available >>");
        return metaInformation;
    }

    public void setMetaInformation(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }
}
