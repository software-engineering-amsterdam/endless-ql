package qls.visitor;

import qls.model.DefaultStyle;
import qls.model.QuestionReference;
import qls.model.Section;
import qls.antlr.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorSection extends VisitorBlock<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String identifier = ctx.STRING().getText();

        // Strip quotes surrounding string
        identifier = identifier.substring(1, identifier.length() - 1);

        List<Section> sections = this.getSections(ctx.section());
        List<QuestionReference> questionReferences = getQuestions(ctx.question());
        List<DefaultStyle> defaultStyles = getDefaults(ctx.defaultStyle());

        return new Section(ctx.getStart(), identifier, sections, questionReferences, defaultStyles);
    }

    private List<QuestionReference> getQuestions(List<QLSParser.QuestionContext> questionContexts) {
        List<QuestionReference> questionReferences = new ArrayList<>();
        if (questionContexts == null) {
            return questionReferences;
        }

        VisitorQuestion visitorQuestion = new VisitorQuestion();
        for (QLSParser.QuestionContext questionContext : questionContexts) {
            QuestionReference questionReference = visitorQuestion.visitQuestion(questionContext);
            questionReferences.add(questionReference);
        }

        return questionReferences;
    }
}
