package ParseObjectQLS;

import java.util.ArrayList;

public class Stylesheet {
    private ArrayList<Page> pages;
    private String identifier;

    public Stylesheet(ArrayList<Page> pages, String identifier){
        setIdentifier(identifier);
        setPages(pages);
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


}
