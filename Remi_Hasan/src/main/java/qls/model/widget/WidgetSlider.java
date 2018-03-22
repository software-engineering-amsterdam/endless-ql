package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetSlider extends Widget {

    public final double min;
    public final double max;
    public final double step;

    public WidgetSlider(Token start, double min, double max, double step) {
        super(start, WidgetType.SLIDER);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
