package com.chariotit.uva.sc.qdsl.ast.visitor;

import com.chariotit.uva.sc.qdsl.ast.node.*;
import com.chariotit.uva.sc.qdsl.ast.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.node.type.BooleanType;
import com.chariotit.uva.sc.qdsl.ast.node.type.IntegerType;
import com.chariotit.uva.sc.qdsl.ast.node.type.MoneyType;
import com.chariotit.uva.sc.qdsl.ast.node.type.StringType;

public abstract class NodeVisitor {

    public abstract void visitBooleanConstant(BooleanConstant booleanConstant);
    public abstract void visitIntegerConstant(IntegerConstant integerConstant);
    public abstract void visitMoneyConstant(MoneyConstant moneyConstant);
    public abstract void visitStringConstant(StringConstant stringConstant);

    public abstract void visitDivideOp(DivideOp divideOp);
    public abstract void visitEqOp(EqOp eqOp);
    public abstract void visitGteOp(GteOp gteOp);
    public abstract void visitGtOp(GtOp gtOp);
    public abstract void visitLteOp(LteOp lteOp);
    public abstract void visitLtOp(LtOp ltOp);
    public abstract void visitMinusOp(MinusOp minusOp);
    public abstract void visitMultiplyOp(MultiplyOp multiplyOp);
    public abstract void visitNeqOp(NeqOp neqOp);
    public abstract void visitNotOp(NotOp notOp);
    public abstract void visitPlusOp(PlusOp plusOp);

    public abstract void visitBooleanType(BooleanType booleanType);
    public abstract void visitIntegerType(IntegerType integerType);
    public abstract void visitMoneyType(MoneyType moneyType);
    public abstract void visitStringType(StringType stringType);

    public abstract void visitAstRoot(AstRoot astRoot);
    public abstract void visitForm(Form form);
    public abstract void visitConstBinOpExpression(ConstBinOpExpression constBinOpExpression);
    public abstract void visitIfBlock(IfBlock ifBlock);
    public abstract void visitLabel(Label label);
    public abstract void visitLabelBinOpExpression(LabelBinOpExpression labelBinOpExpression);
    public abstract void visitLabelExpression(LabelExpression labelExpression);
    public abstract void visitLineElement(LineElement lineElement);
    public abstract void visitQuestion(Question question);
    public abstract void visitTypeExpression(TypeExpression typeExpression);
    public abstract void visitUnOpExpression(UnOpExpression unOpExpression);
}
