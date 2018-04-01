package com.chariotit.uva.sc.qdsl.ast.ql.visitor;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.*;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.IntegerTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;

import java.util.*;

public class TypeCheckVisitor extends NodeVisitor {

    private List<TypeCheckError> errors = new ArrayList<>();
    private SymbolTable symbolTable;
    private Map<String, Question> questions = new HashMap<>();

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
    public void visitOrOp(OrOp orOp) {

    }

    @Override
    public void visitAndOp(AndOp andOp) {

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
    public void visitAstRoot(QLAstRoot astRoot) {

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
        } else if (!constBinOpExpression.getOperator().isValidExpressionType(constBinOpExpression
                .getConstant()
                        .getExpressionType())) {
            addError(constBinOpExpression, "Incompatible operands and operator");
        } else {
            constBinOpExpression.setExpressionType(
                    constBinOpExpression.getOperator().getResultExpressionType(
                            constBinOpExpression.getExpression().getExpressionType()
                    ));
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
        } else if (!labelBinOpExpression.getOperator().isValidExpressionType(labelBinOpExpression
                        .getExpression()
                        .getExpressionType())) {
            addError(labelBinOpExpression, "Incompatible operands and operator");
        } else {
            labelBinOpExpression.setExpressionType(
                    labelBinOpExpression.getOperator().getResultExpressionType(labelBinOpExpression
                                    .getExpression().getExpressionType()
                            ));
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
        if (questions.get(question.getQuestion()) == null) {
            questions.put(question.getQuestion(), question);
        } else {
            addWarning(question, "Duplicate question");
        }
    }

    @Override
    public void visitTypeExpression(TypeExpression typeExpression) {
        if (typeExpression.getExpression() != null && typeExpression.getExpression()
                .getExpressionType() != null) {

            if (typeExpression.getTypeNode().getType() !=
                    typeExpression.getExpression().getExpressionType()) {
                addError(typeExpression, "Type and expression mismatch");
            }
        }
    }

    @Override
    public void visitUnOpExpression(UnOpExpression unOpExpression) {

        if (!unOpExpression.getOperator().isValidExpressionType(unOpExpression.getExpression()
                .getExpressionType())) {

            addError(unOpExpression, "Expression and operator type mismatch");

        } else {
            unOpExpression.setExpressionType(unOpExpression.getExpression().getExpressionType());
        }
    }

    private void addError(AstNode node, String message) {
        errors.add(new TypeCheckError(message, node.getSourceFilePosition()));
    }

    private void addWarning(AstNode node, String message) {
        errors.add(new TypeCheckError(message, node.getSourceFilePosition(),
                TypeCheckError.Level.WARN));
    }

    public List<TypeCheckError> getErrors() {
        return errors;
    }
}
