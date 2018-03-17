package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

import java.util.List;

public class Page extends QLSNode {

    public final String identifier;
    private final List<DefaultStyle> defaultStyles;
    private final List<Section> sections;

    public Page(Token token, String identifier, List<DefaultStyle> defaultStyles, List<Section> sections) {
        super(token);
        this.identifier = identifier;
        this.defaultStyles = defaultStyles;
        this.sections = sections;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public List<DefaultStyle> getDefaultStyles() {
        return defaultStyles;
    }

    public List<Section> getSections() {
        return sections;
    }
}
