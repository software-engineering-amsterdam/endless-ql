package ql.visitors.interfaces;

import ql.evaluator.value.Bool;
import ql.evaluator.value.Date;
import ql.evaluator.value.Decimal;
import ql.evaluator.value.Int;
import ql.evaluator.value.Money;
import ql.evaluator.value.Str;
import ql.evaluator.value.Undefined;
import ql.evaluator.value.Value;

public interface ValueVisitor {

    public Value<?> visit(Bool value);
    public Value<?> visit(Str value);
    public Value<?> visit(Int value);
    public Value<?> visit(Decimal value);
    public Value<?> visit(Money value);
    public Value<?> visit(Date value);
    public Value<?> visit(Undefined value);
}
