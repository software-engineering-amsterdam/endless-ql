package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

import java.util.List;

public class Page extends QLSNode {

    private final String identifier;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyles;

    public Page(Token token, String identifier, List<Section> sections, List<DefaultStyle> defaultStyles) {
        super(token);
        this.identifier = identifier;
        this.sections = sections;
        this.defaultStyles = defaultStyles;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<DefaultStyle> getDefaultStyles() {
        return defaultStyles;
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
