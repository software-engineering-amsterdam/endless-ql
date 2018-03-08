package astvisitor;

import analysis.SymbolTable;
import expression.Expression;
import expression.ExpressionBinary;
import expression.ExpressionIdentifier;
import expression.ReturnType;
import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;
import model.Form;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckVisitor implements IASTVisitor<ReturnType> {

    private final Form form;
    private final SymbolTable symbolTable;
    private final List<String> errors;

    public TypeCheckVisitor(Form form, SymbolTable symbolTable) {
        this.form = form;
        this.symbolTable = symbolTable;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public List<String> typeCheck() {
        for(Question q : form.questions) {
            this.visit(q.condition);

            // Only check expression when it is a predefined expression
            if(!q.isEditable()) {
                this.visit(q.defaultAnswer);
            }
        }

        return this.errors;
    }

    private ReturnType checkBinaryArithmetic(ExpressionBinary e, String operation) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = e.left.accept(this).isNumber() && e.right.accept(this).isNumber();

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-numeric value in expression");
        }

        return ReturnType.NUMBER;
    }

    private ReturnType checkBinaryComparison(ExpressionBinary e, String operation) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = e.left.accept(this).isNumber() && e.right.accept(this).isNumber();

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-numeric value in expression");
        }

        return ReturnType.BOOLEAN;
    }

    private ReturnType checkBinaryBoolean(ExpressionBinary e, String operation) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = e.left.accept(this) == ReturnType.BOOLEAN
                && e.right.accept(this) == ReturnType.BOOLEAN;

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-boolean value in expression");
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(Expression e) {
        return e.accept(this);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticDivide e) {
        return checkBinaryArithmetic(e, "division");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticMultiply e) {
        return checkBinaryArithmetic(e, "multiplication");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSubtract e) {
        return checkBinaryArithmetic(e, "subtraction");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSum e) {
        return checkBinaryArithmetic(e, "addition");
    }

    @Override
    public ReturnType visit(ExpressionComparisonEq e) {
        boolean selfValid = e.left.accept(this) == e.right.accept(this);

        if (!selfValid) {
            errors.add("Invalid EQ: comparing values of different types");
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionComparisonGE e) {
        return checkBinaryComparison(e, "GE");
    }

    @Override
    public ReturnType visit(ExpressionComparisonGT e) {
        return checkBinaryComparison(e, "GT");
    }

    @Override
    public ReturnType visit(ExpressionComparisonLE e) {
        return checkBinaryComparison(e, "LE");
    }

    @Override
    public ReturnType visit(ExpressionComparisonLT e) {
        return checkBinaryComparison(e, "LT");
    }

    @Override
    public ReturnType visit(ExpressionLogicalAnd e) {
        return checkBinaryBoolean(e, "AND");
    }

    @Override
    public ReturnType visit(ExpressionLogicalOr e) {
        return checkBinaryBoolean(e, "OR");
    }

    @Override
    public ReturnType visit(ExpressionUnaryNot e) {
        boolean selfValid = e.value.accept(this) == ReturnType.BOOLEAN;

        if (!selfValid) {
            errors.add("Invalid NOT: non-boolean expression");
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionUnaryNeg e) {
        boolean selfValid = e.value.accept(this) == ReturnType.NUMBER;

        if (!selfValid) {
            errors.add("Invalid NEG: non-numeric expression");
        }

        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableBoolean e) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionVariableDate e) {
        return ReturnType.DATE;
    }

    @Override
    public ReturnType visit(ExpressionVariableInteger e) {
        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableDecimal e) {
        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableMoney e) {
        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableString e) {
        return ReturnType.STRING;
    }

    @Override
    public ReturnType visit(ExpressionVariableUndefined e) {
        return e.getReturnType();
    }

    @Override
    public ReturnType visit(ExpressionIdentifier e) {
        if(this.symbolTable.containsExpression(e.identifier)){
            return this.symbolTable.getExpression(e.identifier).accept(this);
        } else {
            errors.add("Cannot get value for unknown field '" + e.identifier + "'.");
            return ReturnType.UNDEFINED;
        }
    }
}
