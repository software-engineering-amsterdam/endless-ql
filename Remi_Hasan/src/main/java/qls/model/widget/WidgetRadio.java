package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetRadio extends Widget {

    private final String trueLabel;
    private final String falseLabel;

    public WidgetRadio(Token token, String trueLabel, String falseLabel) {
        super(token, WidgetType.RADIO);
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
        return visitor.visitWidgetTypeBooleanRadio(symbolTable, question, defaultStyles, falseLabel, trueLabel);
    }
}
