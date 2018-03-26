package QLS.classes.blocks;

import java.util.List;

public class Section extends Element {
    private String name;
    private List<Element> elements;

    public Section(String name, List<Element> elements) {
        super();
        this.name = name;
        this.elements = elements;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
