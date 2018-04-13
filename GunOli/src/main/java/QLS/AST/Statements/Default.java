package QLS.AST.Statements;

import QL.Analysis.EvaluationType;
import QLS.Analysis.WidgetVisitorInterface;
import QLS.AST.StyleAttribute.Style;
import QLS.AST.Widgets.Widget;

import java.util.List;

public class Default extends Statement {

    private EvaluationType type;
    private Widget widget;
    private List<Style> styles;

    public Default(EvaluationType type, Widget widget, List<Style> styles, int line ){
        super(line);
        this.type = type;
        this.widget = widget;
        this.styles = styles;
    }

    public EvaluationType getType() {
        return type;
    }

    public Widget getWidget() {
        return widget;
    }

    public List<Style> getStyleAttributes() { return styles; }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
