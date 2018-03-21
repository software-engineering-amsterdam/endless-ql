package qls.model.widget;

import gui.WidgetVisitor;
import gui.widgets.DateWidget;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetDefault extends Widget {
    public WidgetDefault(Token start, WidgetType type) {
        super(start, type);
    }

    @Override
    public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        switch(question.type){
            case BOOLEAN:
                return new WidgetCheckBox(null).createWidget(visitor, symbolTable, question, qlsQuestion, defaultStyles);
            case STRING:
            case INTEGER:
            case DECIMAL:
            case MONEY:
                return new WidgetTextBox(null).createWidget(visitor, symbolTable, question, qlsQuestion, defaultStyles);
            case DATE:
                return new WidgetDatePicker(null).createWidget(visitor, symbolTable, question, qlsQuestion, defaultStyles);
            default:
                throw new UnsupportedOperationException("Default widget for type "  + question.type + " unknown.");
        }
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
