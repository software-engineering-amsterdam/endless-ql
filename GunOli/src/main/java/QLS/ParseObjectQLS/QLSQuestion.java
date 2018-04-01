package QLS.ParseObjectQLS;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.ParseObjectQLS.Widgets.Widget;

import java.util.ArrayList;

public class QLSQuestion extends QLSNode {

    private String identifier;
    private ArrayList<Widget> widgets;


    public QLSQuestion(String identifier, ArrayList<Widget> widgets, int line){
        super(line);
        this.identifier = identifier;
        this.widgets = widgets;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayList<Widget> getWidget() {
        return widgets;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
