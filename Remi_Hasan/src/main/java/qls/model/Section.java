package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

import java.util.List;

public class Section extends QLSNode {
    public final String identifier;
    private final List<Section> sections;
    private final List<QuestionReference> questionReferences;
    private final List<DefaultStyle> defaultStyles;

    public Section(Token token, String identifier, List<Section> sections, List<QuestionReference> questionReferences, List<DefaultStyle> defaultStyles) {
        super(token);
        this.identifier = identifier;
        this.sections = sections;
        this.questionReferences = questionReferences;
        this.defaultStyles = defaultStyles;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<QuestionReference> getQuestionReferences() {
        return questionReferences;
    }

    public List<DefaultStyle> getDefaultStyles() {
        return defaultStyles;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
