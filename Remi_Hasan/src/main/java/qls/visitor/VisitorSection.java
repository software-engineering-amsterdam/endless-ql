package qls.visitor;

import qls.model.Question;
import qls.parser.QLSParser;
import qls.model.Default;
import qls.model.Section;

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
        List<Default> defaults = getDefaults(ctx.default_());

        return new Section(ctx.getStart(), identifier, sections, questions, defaults);
    }

    private List<Question> getQuestions(List<QLSParser.QuestionContext> questionContextList) {
        List<Question> questions = new ArrayList<>();
        if(questionContextList == null) {
            return questions;
        }

        VisitorQuestion visitorQuestion = new VisitorQuestion();
        for (QLSParser.QuestionContext questionContext : questionContextList) {
            Question question = visitorQuestion.visitQuestion(questionContext);
            questions.add(question);
        }

        return questions;
    }
}
