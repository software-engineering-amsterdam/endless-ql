package ParseObjectQLS;

import ParseObjectQLS.Widgets.Widget;
import ParseObjects.Expressions.EvaluationType;

import java.util.ArrayList;

public class Default {

    private EvaluationType type;
    private ArrayList<Widget> widgets;

    public Default(EvaluationType type, ArrayList<Widget> widgets ){
        setType(type);
        setWidgets(widgets);
    }

    public EvaluationType getType() {
        return type;
    }

    public void setType(EvaluationType type) {
        this.type = type;
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<Widget> widgets) {
        this.widgets = widgets;
    }
}
