package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

import java.util.List;
import java.util.stream.Collectors;

public class Page extends ASTNode {

    private final String name;
    private final List<BlockElement> elements;

    public Page(MetaInformation metaInformation, String name, List<BlockElement> elements) {
        super(metaInformation);
        this.name = name;
        this.elements = elements;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public List<BlockElement> getElements() {
        return elements;
    }

    public List<DefaultDefinition> getDefaultDefinitions() {
        return this.elements.stream()
                .filter(item -> item instanceof DefaultDefinition)
                .map(item -> (DefaultDefinition) item)
                .collect(Collectors.toList())
                ;
    }
}
