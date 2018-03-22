package ql.visitors.interfaces;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public interface ValueVisitor {

    public Literal<?> visit(BoolLiteral value);
    public Literal<?> visit(StrLiteral value);
    public Literal<?> visit(IntLiteral value);
    public Literal<?> visit(DecimalLiteral value);
    public Literal<?> visit(MoneyLiteral value);
    public Literal<?> visit(DateLiteral value);
    public Literal<?> visit(UndefinedLiteral value);
}
