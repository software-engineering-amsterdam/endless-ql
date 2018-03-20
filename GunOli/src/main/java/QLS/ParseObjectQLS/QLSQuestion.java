package QLS.ParseObjectQLS;

import QLS.ParseObjectQLS.Widgets.Widget;

import java.util.ArrayList;

public class QLSQuestion {

    private String identifier;
    private ArrayList<Widget> widgets;


    public QLSQuestion(String identifier, ArrayList<Widget> widgets){
        setIdentifier(identifier);
        setWidget(widgets);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    public ArrayList<Widget> getWidget() {
        return widgets;
    }

    public void setWidget(ArrayList<Widget> widgets) {
        this.widgets = widgets;
    }
}
