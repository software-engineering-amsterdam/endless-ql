package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

import java.util.List;

public class Stylesheet extends ASTNode {
    private final String name;
    private final List<Page> pageDefinitions;
    private final List<DefaultDefinition> defaultDefinitions;

    public Stylesheet(MetaInformation metaInformation, String name, List<Page> pageDefinitions, List<DefaultDefinition> defaultDefinitions) {
        super(metaInformation);
        this.name = name;
        this.pageDefinitions = pageDefinitions;
        this.defaultDefinitions = defaultDefinitions;
    }

    public void addPageDefiniton(Page pageDefinition) {
        this.pageDefinitions.add(pageDefinition);
    }

    public void addDefaultDefinition(DefaultDefinition defaultDefinition) {
        this.defaultDefinitions.add(defaultDefinition);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public List<Page> getPageDefinitions() {
        return pageDefinitions;
    }

    public List<DefaultDefinition> getDefaultDefinitions() {
        return defaultDefinitions;
    }
}
