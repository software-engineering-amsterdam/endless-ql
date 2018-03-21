package domain.visitor;

import domain.Utilities;
import domain.model.stylesheet.UIElement;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.variable.BooleanVariable;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.Map;
import java.util.Optional;


public class UIVisitor implements Visitor {
    @Override
    public Node visit(BooleanVariable bv) {
        CheckBox cb = new CheckBox();

        System.out.println("update checkbox");

        cb.setSelected(bv.getValueObject().getValue());

        JavaFxObservable
                .valuesOf(cb.selectedProperty())
                .subscribe(bv.getValueObject());
        return cb;

    }

    @Override
    public Node visit(StringVariable sv) {
        TextField tf = new TextField();
        JavaFxObservable
                .valuesOf(tf.textProperty())
                .subscribe(sv.getValueObject());
        return tf;
    }

    @Override
    public Node visit(MoneyVariable mv) {
        if (mv.getValueObject() instanceof ArithmeticExpressionValue){
            Label lbl = new Label();
            lbl.setText(mv.getValueObject().getValue().toString());
            return lbl;
        }else {
            TextField tf = new TextField(String.valueOf(mv.getValueObject().getValue()));
            JavaFxObservable
                    .valuesOf(tf.textProperty())
                    .filter(Utilities::isNumeric)
                    .map(Integer::valueOf)
                    .subscribe(mv.getValueObject());
            return tf;
        }
    }
}
