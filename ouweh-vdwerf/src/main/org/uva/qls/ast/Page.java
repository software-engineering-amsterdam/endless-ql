package org.uva.qls.ast;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Segment.Segment;

import java.util.ArrayList;
import java.util.List;

public class Page extends TreeNode {

    private String id;
    private List<Segment> segments;
    private List<DefaultStatement> defaultStatements;

    public Page(String id, List<Segment> segments, List<DefaultStatement> defaultStatements){
        this.id = id;
        this.segments = segments;
        this.defaultStatements = defaultStatements;
    }

    public List<QuestionReference> getQuestions() {
        List<QuestionReference> questionReferences = new ArrayList<>();
        for (Segment segment : segments) {
            questionReferences.addAll(segment.getQuestions());
        }
        return questionReferences;
    }
}
