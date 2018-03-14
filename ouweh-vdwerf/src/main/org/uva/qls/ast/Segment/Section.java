package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;

import java.util.ArrayList;
import java.util.List;

public class Section extends Segment {

    private String id;
    private List<Segment> segments;
    private List<DefaultStatement> defaultStatements;

    public Section(String id, List<Segment> segments, List<DefaultStatement> defaultStatements){
        this.id = id;
        this.segments = segments;
        this.defaultStatements = defaultStatements;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Segment segment : segments) {
            questions.addAll(segment.getQuestions());
        }
        return questions;
    }
}
