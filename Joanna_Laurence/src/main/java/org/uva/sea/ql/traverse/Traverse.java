package org.uva.sea.ql.traverse;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.DualNode;

public abstract class Traverse {

    public void doAdd(Addition node)  {}

    public void doAnd(And node)  {}

    public void doDiv(Division node)  {}

    public void doEq(Equal node)  {}

    public void doGEq(GreaterOrEqual node)  {}

    public void doGThan(GreaterThan node)  {}

    public void doLEq(LessOrEqual node)  {}

    public void doLThan(LessThan node)  {}

    public void doMul(Multiplication node)  {}

    public void doNeg(Negative node)  {}

    public void doNEq(NotEqual node)  {}

    public void doNot(Not node)  {}

    public void doOr(Or node) {}

    public void doPos(Positive node) {}

    public void doSub(Subtraction node) {}

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
