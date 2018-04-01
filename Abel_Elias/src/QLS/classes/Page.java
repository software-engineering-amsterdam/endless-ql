package QLS.classes;

import QLS.classes.blocks.Section;

import java.util.List;

public class Page {

    private String id;
    private List<Section> sections;

    public Page(String id, List<Section> sections) {
        this.id = id;
        this.sections = sections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
