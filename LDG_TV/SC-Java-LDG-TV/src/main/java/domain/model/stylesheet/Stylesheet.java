package domain.model.stylesheet;

import domain.model.ast.QuestionASTNode;
import domain.model.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet {
    private String label;
    private List<Page> pages;

    public Stylesheet() {
        pages = new ArrayList<>();
    }
    /**
     * Adds a Page to the pages list.
     * @param p Page to add
     */
    public void addPage(Page p) {
        this.pages.add(p);
    }

    /**
     * Get all questionASTNodes which are in the pages and sections.
     * @return List of QuestionASTNodes in pages and sections.
     */
    public List<Variable> getAllVariables(){
        List<Variable> temp = new ArrayList<>();
        for (Page p : getPages()){
            for (Section s : p.getSections()){
                temp.addAll(s.getVariables());
            }
        }
        return temp;
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


}
