package QLS.ParseObjectQLS;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.QLSVisitor.WidgetTable;

import java.util.ArrayList;

public class Stylesheet extends QLSNode {
    private ArrayList<Page> pages;
    private WidgetTable widgetTable;
    private String identifier;

    public Stylesheet(ArrayList<Page> pages, String identifier, WidgetTable widgetTable, int line){
        super(line);
        this.identifier = identifier;
        this.pages = pages;
        this.widgetTable = widgetTable;
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

    public WidgetTable getWidgetTable() {
        return widgetTable;
    }
}
