package domain.model.stylesheet;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String label;
    private List<Section> sections;

    public Page(String label) {
        this.label = label;
        this.sections = new ArrayList<>();
    }

    /**
     * Adds a Section to sections list.
     * @param s section to add.
     */
    public void addSection(Section s) {
        this.sections.add(s);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Section> getSections() {
        return sections;
    }
}
