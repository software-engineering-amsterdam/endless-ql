package ast.model;

abstract public class ASTNode implements VisitableASTNode {

    public static class MetaInformation {

        private Integer startLine;
        private Integer endLine;
        private Integer column;
        private String text;    //TODO: YAGNI?

        public MetaInformation(Integer startLine, Integer endLine, Integer column, String text) {
            this.startLine = startLine;
            this.endLine = endLine;
            this.column = column;
            this.text = text;
        }

        public Integer getStartLine() {
            return startLine;
        }

        public Integer getEndLine() {
            return endLine;
        }

        public Integer getColumn() {
            return column;
        }

        public String getText() {
            return text;
        }

    }

    private MetaInformation metaInformation;

    public ASTNode(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }

    public MetaInformation getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }
}
