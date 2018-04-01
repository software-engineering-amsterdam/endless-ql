package domain.visitor;

import domain.Utilities;
import domain.model.variable.BooleanVariable;
import domain.model.variable.MoneyVariable;
import domain.model.variable.StringVariable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class UIVisitor implements Visitor {
    @Override
    public Node visit(BooleanVariable bv) {
        CheckBox cb = new CheckBox();

        cb.setSelected(bv.getComputedValue());

        JavaFxObservable
                .valuesOf(cb.selectedProperty())
                .subscribe(bv);
        return cb;

    }

    @Override
    public Node visit(StringVariable sv) {
        TextField tf = new TextField();
        JavaFxObservable
                .valuesOf(tf.textProperty())
                .subscribe(sv);
        return tf;
    }

    @Override
    public Node visit(MoneyVariable mv) {
        TextField tf = new TextField(String.valueOf(mv.getComputedValue()));
        JavaFxObservable
                .valuesOf(tf.textProperty())
                .filter(Utilities::isNumeric)
                .map(Integer::valueOf)
                .subscribe(mv);
        return tf;
    }
}
