package QLS.ParseObjectQLS;

import QLS.Analysis.WidgetVisitorInterface;

import java.util.ArrayList;

public class Stylesheet extends QLSNode {
    private ArrayList<Page> pages;

    private String identifier;

    public Stylesheet(ArrayList<Page> pages, String identifier, int line){
        super(line);
        this.identifier = identifier;
        this.pages = pages;

    }


    public ArrayList<Page> getPages() {
        return pages;
    }

    public String getIdentifier() {
        return identifier;
    }



    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }

}
