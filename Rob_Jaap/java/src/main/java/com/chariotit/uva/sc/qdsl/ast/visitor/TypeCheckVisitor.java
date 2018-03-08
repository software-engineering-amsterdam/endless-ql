package com.chariotit.uva.sc.qdsl.ast.visitor;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.node.type.*;
import com.chariotit.uva.sc.qdsl.ast.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.node.*;
import com.chariotit.uva.sc.qdsl.ast.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.node.type.IntegerTypeNode;
import com.chariotit.uva.sc.qdsl.ast.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.ast.symboltable.SymbolTableEntry;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckVisitor extends NodeVisitor {

    private List<TypeCheckError> errors = new ArrayList<>();
    private SymbolTable symbolTable;

    public TypeCheckVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public void visitBooleanConstant(BooleanConstant booleanConstant) {
        booleanConstant.setExpressionType(ExpressionType.BOOLEAN);
    }

    @Override
    public void visitIntegerConstant(IntegerConstant integerConstant) {
        integerConstant.setExpressionType(ExpressionType.INTEGER);
    }

    @Override
    public void visitMoneyConstant(MoneyConstant moneyConstant) {
        moneyConstant.setExpressionType(ExpressionType.MONEY);
    }

    @Override
    public void visitStringConstant(StringConstant stringConstant) {
        stringConstant.setExpressionType(ExpressionType.STRING);
    }

    @Override
    public void visitDivideOp(DivideOp divideOp) {

    }

    @Override
    public void visitEqOp(EqOp eqOp) {

    }

    @Override
    public void visitGteOp(GteOp gteOp) {

    }

    @Override
    public void visitGtOp(GtOp gtOp) {

    }

    @Override
    public void visitLteOp(LteOp lteOp) {

    }

    @Override
    public void visitLtOp(LtOp ltOp) {

    }

    @Override
    public void visitMinusOp(MinusOp minusOp) {

    }

    @Override
    public void visitMultiplyOp(MultiplyOp multiplyOp) {

    }

    @Override
    public void visitNeqOp(NeqOp neqOp) {

    }

    @Override
    public void visitNotOp(NotOp notOp) {

    }

    @Override
    public void visitPlusOp(PlusOp plusOp) {

    }

    @Override
    public void visitBooleanType(BooleanTypeNode booleanType) {

    }

    @Override
    public void visitIntegerType(IntegerTypeNode integerType) {

    }

    @Override
    public void visitMoneyType(MoneyTypeNode moneyType) {

    }

    @Override
    public void visitStringType(StringTypeNode stringType) {

    }

    @Override
    public void visitAstRoot(AstRoot astRoot) {

    }

    @Override
    public void visitForm(Form form) {

    }

    @Override
    public void visitConstBinOpExpression(ConstBinOpExpression constBinOpExpression) {
        if (constBinOpExpression.getExpression().getExpressionType() == null) {
            // Visiting children returned errors
            return;
        }

        if (constBinOpExpression.getConstant().getExpressionType() != constBinOpExpression
                .getExpression().getExpressionType()) {
            addError(constBinOpExpression, "Incompatible operands");
        } else if (!checkOperatorType(constBinOpExpression.getConstant().getExpressionType(),
                constBinOpExpression.getOperator())) {
            addError(constBinOpExpression, "Incompatible operands and operator");
        } else {
            constBinOpExpression.setExpressionType(constBinOpExpression.getExpression().getExpressionType());
        }
    }

    @Override
    public void visitIfBlock(IfBlock ifBlock) {
        if (ifBlock.getExpression().getExpressionType() != ExpressionType.BOOLEAN) {
            addError(ifBlock, "If condition is not of type boolean");
        }
    }

    @Override
    public void visitLabel(Label label) {

    }

    @Override
    public void visitLabelBinOpExpression(LabelBinOpExpression labelBinOpExpression) {
        if (labelBinOpExpression.getExpression().getExpressionType() == null ||
                labelBinOpExpression.getLabelExpression().getExpressionType() == null) {
            // Visiting children returned errors
            return;
        }

        if (labelBinOpExpression.getExpression().getExpressionType() !=
                labelBinOpExpression.getLabelExpression().getExpressionType()) {
            addError(labelBinOpExpression, "Incompatible operands");
        } else if (!checkOperatorType(labelBinOpExpression.getExpression().getExpressionType(),
                labelBinOpExpression.getOperator())) {
            addError(labelBinOpExpression, "Incompatible operands and operator");
        } else {
            labelBinOpExpression.setExpressionType(labelBinOpExpression.getExpression().getExpressionType());
        }
    }

    @Override
    public void visitLabelExpression(LabelExpression labelExpression) {
        SymbolTableEntry symbolTableEntry = symbolTable.getEntry(labelExpression.getLabel());

        if (symbolTableEntry == null) {
            addError(labelExpression, "Label is not declared");
        } else {
            labelExpression.setExpressionType(symbolTableEntry.getExpressionType());
        }
    }

    @Override
    public void visitLineElement(LineElement lineElement) {
    }

    @Override
    public void visitQuestion(Question question) {

    }

    @Override
    public void visitTypeExpression(TypeExpression typeExpression) {
        if (typeExpression.getExpression() != null && typeExpression.getExpression()
                .getExpressionType() != null) {

            switch (typeExpression.getExpression().getExpressionType()) {
                case BOOLEAN:
                    if (!(typeExpression.getTypeNode() instanceof BooleanTypeNode)) {
                        addError(typeExpression, "Type and expression mismatch");
                    }
                    break;
                case INTEGER:
                    if (!(typeExpression.getTypeNode() instanceof IntegerTypeNode)) {
                        addError(typeExpression, "Type and expression mismatch");
                    }
                    break;
                case MONEY:
                    if (!(typeExpression.getTypeNode() instanceof MoneyTypeNode)) {
                        addError(typeExpression, "Type and expression mismatch");
                    }
                    break;
                case STRING:
                    if (!(typeExpression.getTypeNode() instanceof StringTypeNode)) {
                        addError(typeExpression, "Type and expression mismatch");
                    }
                    break;
                default:
                    throw new RuntimeException("Missing type");
            }
        }
    }

    @Override
    public void visitUnOpExpression(UnOpExpression unOpExpression) {

        if (!checkOperatorType(unOpExpression.getExpression().getExpressionType(),
                               unOpExpression.getOperator())) {

            addError(unOpExpression, "Expression and operator type mismatch");

        } else {
            unOpExpression.setExpressionType(unOpExpression.getExpression().getExpressionType());
        }
    }

    private void addError(AstNode node, String message) {
        errors.add(new TypeCheckError(message, node.getLineNumber(), node.getColumnNumber()));
    }

    private boolean checkOperatorType(ExpressionType type, Operator operator) {
        switch (type) {
            case BOOLEAN:
                return operator instanceof BooleanOperator;
            case INTEGER:
                return operator instanceof IntegerOperator;
            case MONEY:
                return operator instanceof MoneyOperator;
            case STRING:
                return operator instanceof StringOperator;
            default:
                throw new RuntimeException("Missing operator type");
        }
    }

    public List<TypeCheckError> getErrors() {
        return errors;
    }
}
