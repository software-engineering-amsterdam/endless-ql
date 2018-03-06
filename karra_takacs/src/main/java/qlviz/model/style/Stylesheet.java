package qlviz.model.style;

import java.util.List;

public class Stylesheet {

    private final List<Page> pages;
    private final String name;

    public Stylesheet(List<Page> pages, String name) {
        this.pages = pages;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Page> getPages() {
        return pages;
    }
}
