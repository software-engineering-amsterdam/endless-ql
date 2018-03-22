package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetSpinBox extends Widget {
    public WidgetSpinBox(Token start) {
        super(start, WidgetType.SPINBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return null;
    }
}
