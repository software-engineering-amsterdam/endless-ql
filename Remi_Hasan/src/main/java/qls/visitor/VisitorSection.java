package qls.visitor;

import qls.model.DefaultStyle;
import qls.model.Question;
import qls.model.Section;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorSection extends VisitorBlock<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String identifier = ctx.STRING().getText();

        // Strip quotes surrounding string
        identifier = identifier.substring(1, identifier.length() - 1);

        List<Section> sections = this.getSections(ctx.section());
        List<Question> questions = getQuestions(ctx.question());
        List<DefaultStyle> defaultStyles = getDefaults(ctx.defaultStyle());

        return new Section(ctx.getStart(), identifier, sections, questions, defaultStyles);
    }

    private List<Question> getQuestions(List<QLSParser.QuestionContext> questionContexts) {
        List<Question> questions = new ArrayList<>();
        if (questionContexts == null) {
            return questions;
        }

        VisitorQuestion visitorQuestion = new VisitorQuestion();
        for (QLSParser.QuestionContext questionContext : questionContexts) {
            Question question = visitorQuestion.visitQuestion(questionContext);
            questions.add(question);
        }

        return questions;
    }
}
