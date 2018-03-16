package qls.model;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Section extends QLSNode {
    public final String identifier;
    public final List<Section> sections;
    public final List<Question> questions;
    public final List<Default> defaults;

    public Section(Token token, String identifier, List<Section> sections, List<Question> questions, List<Default> defaults) {
        super(token);
        this.identifier = identifier;
        this.sections = sections;
        this.questions = questions;
        this.defaults = defaults;
    }
}
