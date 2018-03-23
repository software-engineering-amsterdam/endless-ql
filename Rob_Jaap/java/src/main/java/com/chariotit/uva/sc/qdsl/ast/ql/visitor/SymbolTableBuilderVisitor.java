package com.chariotit.uva.sc.qdsl.ast.ql.visitor;

import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.BooleanTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.IntegerTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.exception.DuplicateSymbolMismatchException;

import java.util.ArrayList;
import java.util.List;

public class SymbolTableBuilderVisitor extends NodeVisitor {

    private List<TypeCheckError> errors = new ArrayList<>();
    private SymbolTable questionSymbolTable = new SymbolTable();
    private SymbolTable formSymbolTable = new SymbolTable();

    @Override
    public void visitBooleanConstant(BooleanConstant booleanConstant) {

    }

    @Override
    public void visitIntegerConstant(IntegerConstant integerConstant) {

    }

    @Override
    public void visitMoneyConstant(MoneyConstant moneyConstant) {

    }

    @Override
    public void visitStringConstant(StringConstant stringConstant) {

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
        astRoot.setQuestionSymbolTable(questionSymbolTable);
        astRoot.setFormSymbolTable(formSymbolTable);
    }

    @Override
    public void visitForm(Form form) {
        try {
            formSymbolTable.addEntry(new SymbolTableEntry(
                    form.getLabel(),
                    form));
        } catch (DuplicateSymbolMismatchException exception) {
            addError(form, exception.getMessage());
        }
    }

    @Override
    public void visitConstBinOpExpression(ConstBinOpExpression constBinOpExpression) {
    }

    @Override
    public void visitIfBlock(IfBlock ifBlock) {

    }

    @Override
    public void visitLabel(Label label) {

    }

    @Override
    public void visitLabelBinOpExpression(LabelBinOpExpression labelBinOpExpression) {

    }

    @Override
    public void visitLabelExpression(LabelExpression labelExpression) {

    }

    @Override
    public void visitLineElement(LineElement lineElement) {

        try {
            questionSymbolTable.addEntry(new SymbolTableEntry(
                    lineElement.getLabel().getLabel(),
                    lineElement,
                    lineElement.getTypeExpression().getTypeNode().getType()
            ));
        } catch (DuplicateSymbolMismatchException exception) {
            addError(lineElement, exception.getMessage());
        }
    }

    @Override
    public void visitQuestion(Question question) {

    }

    @Override
    public void visitTypeExpression(TypeExpression typeExpression) {

    }

    @Override
    public void visitUnOpExpression(UnOpExpression unOpExpression) {

    }

    private void addError(AstNode node, String message) {
        errors.add(new TypeCheckError(message, node.getSourceFilePosition()));
    }

    public List<TypeCheckError> getErrors() {
        return errors;
    }
}
