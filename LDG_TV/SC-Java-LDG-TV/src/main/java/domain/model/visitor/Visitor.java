package domain.model.visitor;

import domain.model.variable.BooleanVariable;
import domain.model.variable.StringVariable;
import javafx.scene.Node;


public interface Visitor {
    Node visit(BooleanVariable bv);
    Node visit(StringVariable sv);
}
