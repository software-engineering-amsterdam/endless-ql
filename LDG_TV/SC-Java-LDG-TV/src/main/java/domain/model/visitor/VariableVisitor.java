package domain.model.visitor;

import domain.model.variable.BooleanVariable;
import domain.model.variable.StringVariable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class VariableVisitor implements Visitor {
    @Override
    public Node visit(BooleanVariable bv) {
        return (Node) new CheckBox();
    }

    @Override
    public Node visit(StringVariable sv) {
        return (Node) new TextField(sv.getValue());
    }
}
