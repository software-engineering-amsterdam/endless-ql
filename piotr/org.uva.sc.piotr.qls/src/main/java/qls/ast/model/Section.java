package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

import java.util.List;
import java.util.stream.Collectors;

public class Section extends BlockElement {

    private final String name;
    private final List<BlockElement> elements;

    public Section(MetaInformation metaInformation, String name, List<BlockElement> elements) {
        super(metaInformation);
        this.name = name;
        this.elements = elements;
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

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
