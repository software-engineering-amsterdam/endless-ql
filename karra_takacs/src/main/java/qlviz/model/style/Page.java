package qlviz.model.style;

import java.util.List;

public class Page {

    private final String name;
    private final List<Section> sections;

    public Page(String name, List<Section> sections) {
        this.name = name;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return sections;
    }
}
