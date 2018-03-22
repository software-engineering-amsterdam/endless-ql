package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.model.DefaultStyle;
import qls.model.QLSNode;

import java.util.List;

public abstract class Widget extends QLSNode {

    public final WidgetType type;

    Widget(Token start, WidgetType type) {
        super(start);
        this.type = type;
    }
}