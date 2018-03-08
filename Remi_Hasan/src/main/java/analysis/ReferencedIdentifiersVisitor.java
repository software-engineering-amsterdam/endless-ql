package analysis;

import evaluation.IASTVisitor;
import model.Form;
import model.Question;
import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;

import java.util.ArrayList;
import java.util.List;

public class ReferencedIdentifiersVisitor implements IASTVisitor<List<String>> {

    private final Form form;

    public ReferencedIdentifiersVisitor(Form form){
        this.form = form;
    }

    public List<String> getUnknownReferencedIdentifiers(){
        List<String> formQuestionIdentifiers = new ArrayList<>();
        List<String> referencedIdentifiers = new ArrayList<>();
        for(Question question : form.questions){
            formQuestionIdentifiers.add(question.name);
            referencedIdentifiers.addAll(this.visit(question.defaultAnswer));
            referencedIdentifiers.addAll(this.visit(question.condition));
        }

        // Determine which identifiers are referenced but no question exists with such identifier
        // Subtraction of formQuestionIdentifiers - referencedIdentifiers
        List<String> unknownReferencedIdentifiers = new ArrayList<>();
        unknownReferencedIdentifiers.addAll(referencedIdentifiers);
        unknownReferencedIdentifiers.removeAll(formQuestionIdentifiers);

        return unknownReferencedIdentifiers;
    }

    private List<String> visitLeftRight(Expression left, Expression right) {
        List<String> identifiers = left.accept(this);
        identifiers.addAll(right.accept(this));
        return identifiers;
    }

    @Override
    public List<String> visit(Expression e) {
        return e.accept(this);
    }

    @Override
    public List<String> visit(ExpressionArithmeticDivide e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticMultiply e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSubtract e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSum e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonEq e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGE e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGT e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLE e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLT e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalAnd e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalOr e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionUnaryNot e) {
        return e.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionUnaryNeg e) {
        return e.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionVariableBoolean e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDate e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableInteger e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDecimal e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableMoney e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableString e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableUndefined e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionIdentifier e) {
        List<String> listWithIdentifier = new ArrayList<>();
        listWithIdentifier.add(e.identifier);
        return listWithIdentifier;
    }
}
