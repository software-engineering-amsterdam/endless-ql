package org.uva.qls.ast;

import org.uva.qls.ast.Segment.Question;
import org.uva.qls.ast.Segment.Segment;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet extends TreeNode{

    private String id;
    private List<Page> pages;

    public Stylesheet(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Page page : pages) {
            questions.addAll(page.getQuestions());
        }
        return questions;
    }
}
