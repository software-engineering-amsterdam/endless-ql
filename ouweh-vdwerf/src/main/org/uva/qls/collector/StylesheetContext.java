package org.uva.qls.collector;

import org.uva.qls.ast.Page;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Segment.Section;
import org.uva.qls.ast.Segment.Segment;
import org.uva.qls.ast.Stylesheet;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StylesheetContext implements SegmentVisitor<Segment> {

    private final HashMap<String, Section> sections;
    private final HashMap<String, QuestionReference> questions;
    private final HashMap<String, Page> pages;

    private final Stylesheet stylesheet;

    public StylesheetContext(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;

        sections = new HashMap<>();
        questions = new HashMap<>();
        pages = new HashMap<>();

        for (Page page : stylesheet.getPages()) {
            pages.put(page.getId(), page);
            for (Segment segment : page.getSegments()) {
                segment.accept(this);
            }
        }
    }

    public List<QuestionReference> getQuestions() {
        return new ArrayList<>(questions.values());
    }

    public QuestionReference getQuestion(String questionId) {
        if (questions.containsKey(questionId)){
            return questions.get(questionId);
        }
        return null;
    }


    @Override
    public Segment visit(Section section) {
        sections.put(section.getId(), section);

        for (Segment segment : section.getSegments()) {
            segment.accept(this);
        }
        return section;
    }

    @Override
    public Segment visit(QuestionReference questionReference) {
        questions.put(questionReference.getId(), questionReference);
        return questionReference;
    }
}
