package qls.visitor;

import qls.model.Question;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;
import qls.model.Default;
import qls.model.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorSection extends QLSBaseVisitor<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String identifier = ctx.STRING().getText();

        // Strip quotes surrounding string
        identifier = identifier.substring(1, identifier.length() - 1);

        List<Section> sections = this.getSections(ctx.section());
        List<Default> defaults = getDefaults(ctx.default_());
        List<Question> questions = getQuestions(ctx.question());

        return new Section(identifier, sections, defaults, questions);
    }

    private List<Section> getSections(List<QLSParser.SectionContext> sectionContextList) {
        List<Section> sections = new ArrayList<>();
        if(sectionContextList == null) {
            return sections;
        }

        VisitorSection visitorSection = new VisitorSection();
        for (QLSParser.SectionContext sectionContext : sectionContextList) {
            Section section = visitorSection.visitSection(sectionContext);
            sections.add(section);
        }

        return sections;
    }

    private List<Default> getDefaults(List<QLSParser.Default_Context> defaultContextList) {
        List<Default> defaults = new ArrayList<>();
        if(defaultContextList == null) {
            return defaults;
        }

        VisitorDefault visitorDefault = new VisitorDefault();
        for (QLSParser.Default_Context defaultContext : defaultContextList) {
            Default default_ = visitorDefault.visitDefault_(defaultContext);
            defaults.add(default_);
        }

        return defaults;
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
