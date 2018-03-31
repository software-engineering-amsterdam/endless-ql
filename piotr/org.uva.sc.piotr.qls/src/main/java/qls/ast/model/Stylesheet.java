package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

import java.util.List;

public class Stylesheet extends ASTNode {
    private final String name;
    private final List<Page> pages;
    private final List<DefaultDefinition> defaultDefinitions;

    public Stylesheet(MetaInformation metaInformation, String name, List<Page> pages, List<DefaultDefinition> defaultDefinitions) {
        super(metaInformation);
        this.name = name;
        this.pages = pages;
        this.defaultDefinitions = defaultDefinitions;
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

    public List<DefaultDefinition> getDefaultDefinitions() {
        return defaultDefinitions;
    }
}
