package qls.model.widget;

import gui.WidgetVisitor;
import gui.widgets.WidgetInterface;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetDropdown extends Widget {

    private final String trueLabel;
    private final String falseLabel;

    public WidgetDropdown(Token token, String trueLabel, String falseLabel) {
        super(token, WidgetType.DROPDOWN);
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public WidgetInterface createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return visitor.visitWidgetTypeBooleanDropdown(symbolTable, question, qlsQuestion, defaultStyles, falseLabel, trueLabel);
    }
}
