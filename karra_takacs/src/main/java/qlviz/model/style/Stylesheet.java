package qlviz.model.style;

import java.util.List;

public class Stylesheet {

    private final List<Page> pages;


    public Stylesheet(List<Page> pages) {
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }
}
