package ql.gui.controls;

import ql.ast.expressions.Identifier;
import ql.ast.statements.Question;
import ql.gui.OnValueChange;
import ql.types.Boolean;
import ql.types.Integer;
import ql.types.Money;
import ql.types.String;
import ql.visitors.TypeVisitor;

public class ControlVisitor implements TypeVisitor<QLControl> {
    private final OnValueChange onValueChange;
    private Identifier identifier;

    public ControlVisitor(OnValueChange onValueChange) {
        this.onValueChange = onValueChange;
    }

    public QLControl createQLControl(Question question) {
        identifier = question.getIdentifier();
        QLControl qlControl = question.getType().accept(this);
        qlControl.setLabel(question.getDescription().toString());
        return qlControl;
    }
    @Override
    public QLControl visit(Boolean bool) {
        return new QLCheckBox(identifier, onValueChange);
    }

    @Override
    public QLControl visit(Integer integer) {
        return new QLIntegerField();
    }

    @Override
    public QLControl visit(Money money) {
        return new QLMoneyField(identifier, onValueChange);
    }

    @Override
    public QLControl visit(String string) {
        return new QLTextField();
    }
}
