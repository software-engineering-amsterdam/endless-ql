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

        public void setStartLine(Integer startLine) {
            this.startLine = startLine;
        }

        public Integer getEndLine() {
            return endLine;
        }

        public void setEndLine(Integer endLine) {
            this.endLine = endLine;
        }

        public Integer getColumn() {
            return column;
        }

        public void setColumn(Integer column) {
            this.column = column;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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
