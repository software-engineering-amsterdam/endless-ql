package org.uva.sea.ql.traverse;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.DualNode;

public abstract class Traverse {

    public void doAdd(Add node)  {}

    public void doAnd(And node)  {}

    public void doDiv(Div node)  {}

    public void doEq(Eq node)  {}

    public void doGEq(GEq node)  {}

    public void doGThan(GThan node)  {}

    public void doLEq(LEq node)  {}

    public void doLThan(LThan node)  {}

    public void doMul(Mul node)  {}

    public void doNeg(Neg node)  {}

    public void doNEq(NEq node)  {}

    public void doNot(Not node)  {}

    public void doOr(Or node) {}

    public void doPos(Pos node) {}

    public void doSub(Sub node) {}

    public void doBool(Bool node) {}

    public void doDateExpr(DateExpr node) {}

    public void doDec(Dec node) {}

    public void doMoney(Money node) {}

    public void doInt(Int node) {}

    public void doStr(Str node) {}

    public void doType(Type node) {}

    public void doVar(Var node) {}

    public void doCondition(Condition node) {}

    public void doForm(Form node) {}

    public void doQuestion(Question node) {}

    public void doStatement(Statement node) {}

    public void doStatements(Statements node) {}

    public void doDualNode(DualNode node) {}

    public void doLogical(DualNode node) {}

    public void doOperation(DualNode node) {}
}
