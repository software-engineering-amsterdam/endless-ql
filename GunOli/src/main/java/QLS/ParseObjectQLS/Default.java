package QLS.ParseObjectQLS;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.ParseObjectQLS.Widgets.Widget;
import QL.Analysis.EvaluationType;

import java.util.ArrayList;

public class Default extends QLSNode {

    private EvaluationType type;
    private ArrayList<Widget> widgets;

    public Default(EvaluationType type, ArrayList<Widget> widgets, int line ){
        super(line);
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

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
