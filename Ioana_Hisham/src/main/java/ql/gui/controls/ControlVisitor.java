package ql.gui.controls;

import ql.ast.statements.Question;
import ql.types.Boolean;
import ql.types.Integer;
import ql.types.Money;
import ql.types.String;
import ql.visitors.TypeVisitor;

public class ControlVisitor implements TypeVisitor<QLControl> {

    public QLControl createQLControl(Question question) {
        QLControl qlControl = question.getType().accept(this);
        qlControl.setLabel(question.getDescription().toString());
        return qlControl;
    }
    @Override
    public QLControl visit(Boolean bool) {
        return new QLCheckBox();
    }

    @Override
    public QLControl visit(Integer integer) {
        return new QLIntegerField();
    }

    @Override
    public QLControl visit(Money money) {
        return new QLMoneyField();
    }

    @Override
    public QLControl visit(String string) {
        return new QLTextField();
    }
}
