package domain.model.stylesheet;

import domain.model.QuestionASTNode;
import exception.ReferenceUndefinedVariableException;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet {
    private String label;
    private List<Page> pages;

    public Stylesheet() {
        pages = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void addPage(Page p) {
        this.pages.add(p);
    }

    public List<QuestionASTNode> getAllQuestions(){
        List<QuestionASTNode> temp = new ArrayList<>();
        for (Page p : getPages()){
            for (Section s : p.getSections()){
                temp.addAll(s.getQuestions());
            }
        }
        return temp;
    }
}
