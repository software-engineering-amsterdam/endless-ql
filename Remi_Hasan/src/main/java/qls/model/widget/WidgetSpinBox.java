package qls.model.widget;

import gui.WidgetVisitor;
import gui.widgets.WidgetInterface;
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

    @Override
    public WidgetInterface createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        switch(question.type){
            case INTEGER:
                return visitor.visitWidgetTypeIntegerSpinBox(symbolTable, question, qlsQuestion, defaultStyles);
            case DECIMAL:
                return visitor.visitWidgetTypeDecimalSpinBox(symbolTable, question, qlsQuestion, defaultStyles);
            case MONEY:
                return visitor.visitWidgetTypeMoneySpinBox(symbolTable, question, qlsQuestion, defaultStyles);
            default:
                throw new UnsupportedOperationException("Unknown type for spinbox");
        }
    }
}
