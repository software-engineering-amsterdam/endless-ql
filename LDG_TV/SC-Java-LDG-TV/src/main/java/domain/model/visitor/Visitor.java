package domain.model.visitor;

import domain.model.variable.BooleanVariable;
import domain.model.value.ExpressionValue;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import javafx.scene.Node;


public interface Visitor {
    Node visit(BooleanVariable bv);
    Node visit(StringVariable sv);
    Node visit(MoneyVariable mv);
    Node visit(ExpressionValue ev);
}
