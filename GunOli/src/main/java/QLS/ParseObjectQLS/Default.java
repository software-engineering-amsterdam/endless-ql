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
        this.type = type;
        this.widgets = widgets;
    }

    public EvaluationType getType() {
        return type;
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
