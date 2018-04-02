package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

import java.util.List;

public class Stylesheet extends ASTNode {
    private final String name;
    private final List<Page> pages;

    public Stylesheet(MetaInformation metaInformation, String name, List<Page> pages) {
        super(metaInformation);
        this.name = name;
        this.pages = pages;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public List<Page> getPages() {
        return pages;
    }

}
