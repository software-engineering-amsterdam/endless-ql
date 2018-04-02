package QLS.classes;

import java.util.List;

public class Stylesheet {
    private String id;
    private List<Page> pages;

    public Stylesheet(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Page> getPages() {
        return pages;
    }

}