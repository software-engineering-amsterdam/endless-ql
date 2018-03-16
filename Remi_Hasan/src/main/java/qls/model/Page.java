package qls.model;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Page extends QLSNode {

    public final String identifier;
    private final List<Default> defaults;
    private final List<Section> sections;

    public Page(Token token, String identifier, List<Default> defaults, List<Section> sections) {
        super(token);
        this.identifier = identifier;
        this.defaults = defaults;
        this.sections = sections;
    }
}
