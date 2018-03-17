package org.uva.qls.collector;

import org.uva.qls.ast.Segment.Page;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Segment.Section;
import org.uva.qls.ast.Segment.Segment;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StylesheetContext implements SegmentVisitor<Segment> {

    private final HashMap<String, Section> sections = new HashMap<>();
    private final HashMap<String, QuestionReference> questions = new HashMap<>();
    private final HashMap<String, Page> pages = new HashMap<>();
    private final HashMap<String, Segment> parents = new HashMap<>();

    private final Stylesheet stylesheet;

    public StylesheetContext(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;


    }

    public List<QuestionReference> getQuestions() {
        return new ArrayList<>(questions.values());
    }

    public List<Page> getPages() {
        return new ArrayList<>(pages.values());
    }

    public List<Section> getSections() {
        return new ArrayList<>(sections.values());
    }

    public Segment getParent(String segmentId){
        if (parents.containsKey(segmentId)){
            return parents.get(segmentId);
        }
        return null;
    }

    public QuestionReference getQuestion(String questionId) {
        if (questions.containsKey(questionId)){
            return questions.get(questionId);
        }
        return null;
    }


    @Override
    public Segment visit(Section section, Segment parent) {
        sections.put(section.getId(), section);
        parents.put(section.getId(), parent);

        for (Segment segment : section.getSegments()) {
            segment.accept(this, section);
        }
        return section;
    }

    @Override
    public Segment visit(QuestionReference questionReference, Segment parent) {
        questions.put(questionReference.getId(), questionReference);
        parents.put(questionReference.getId(), parent);
        return questionReference;
    }

    @Override
    public Segment visit(Page page, Segment parent) {
        pages.put(page.getId(), page);
        parents.put(page.getId(), parent);

        for (Segment segment : page.getSegments()) {
            segment.accept(this, page);
        }

        return page;
    }

    @Override
    public Segment visit(Stylesheet stylesheet) {
        for (Page page : stylesheet.getPages()){
            page.accept(this, null);
        }
        return stylesheet;
    }


}
