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

    @Override
    public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
        switch(question.type){
            case INTEGER:
                return visitor.visitWidgetTypeIntegerSlider(symbolTable, question, defaultStyles, (int)min, (int)max, (int)step);
            case DECIMAL:
                return visitor.visitWidgetTypeDecimalSlider(symbolTable, question, defaultStyles, min, max, step);
            case MONEY:
                return visitor.visitWidgetTypeMoneySlider(symbolTable, question, defaultStyles, min, max, step);
            default:
                throw new UnsupportedOperationException("Unknown type for spinbox");
        }
    }
}
