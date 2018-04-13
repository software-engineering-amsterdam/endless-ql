package QLS.AST.Widgets;

import QLS.Analysis.WidgetVisitorInterface;

import java.util.ArrayList;

public class Radio extends Widget {

    private ArrayList<String> values;
    public Radio(ArrayList<String> values, int line) {
        super(WidgetType.Radio, line);
        this.values = values;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
