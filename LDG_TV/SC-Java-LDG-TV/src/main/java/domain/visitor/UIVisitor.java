package domain.visitor;

import domain.model.value.ExpressionValue;
import domain.model.variable.BooleanVariable;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class UIVisitor implements Visitor {
    @Override
    public Node visit(BooleanVariable bv) {
        CheckBox cb = new CheckBox();
        cb.selectedProperty()
                .addListener((observable, oldValue, newValue) -> bv.setValue(newValue));
        return cb;
    }

    @Override
    public Node visit(StringVariable sv) {
        TextField tf = new TextField();

        tf.textProperty()
                .addListener((observable, oldValue, newValue) -> sv.setValue(newValue));

        return tf;
    }

    @Override
    public Node visit(ExpressionValue ev) {
        return (Node) new Label();
    }

    @Override
    public Node visit(MoneyVariable mv) {
        return (Node) new TextField();
    }
}
