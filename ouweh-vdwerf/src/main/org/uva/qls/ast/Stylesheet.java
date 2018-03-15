package org.uva.qls.ast;

import org.uva.qls.ast.Segment.QuestionReference;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet extends TreeNode{

    private String id;
    private List<Page> pages;

    public Stylesheet(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public List<QuestionReference> getQuestions() {
        List<QuestionReference> questionReferences = new ArrayList<>();
        for (Page page : pages) {
            questionReferences.addAll(page.getQuestions());
        }
        return questionReferences;
    }
}
