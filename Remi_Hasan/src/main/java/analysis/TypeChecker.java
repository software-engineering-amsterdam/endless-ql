package analysis;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionBinary;
import model.expression.ExpressionIdentifier;
import model.expression.ReturnType;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;
import model.Form;
import model.Question;

import java.util.*;

public class TypeChecker implements IASTVisitor<ReturnType> {

    private final Form form;
    private final SymbolTable symbolTable;
    private final Set<String> errors;

    public TypeChecker(Form form, SymbolTable symbolTable) {
        this.form = form;
        this.symbolTable = symbolTable;
        this.errors = new HashSet<>();
    }

    public Set<String> getErrors() {
        return this.errors;
    }

    public Set<String> typeCheck() {
        for (Question question : form.questions) {
            this.visit(question.condition);

            // Only check expression when it is a predefined expression
            if (!question.isEditable()) {
                ReturnType defaultAnswerType = this.visit(question.defaultAnswer);

                // Check if question type is same as assigned expression type
                ReturnType questionType = question.type;
                if(questionType.isNumber()) {
                    questionType = ReturnType.NUMBER;
                }

                if (defaultAnswerType != questionType) {
                    errors.add("Invalid assignment: cannot assign " + defaultAnswerType + " to " + question.type +
                            question.getLocation());
                }
            }
        }

        return this.errors;
    }

    public Set<String> getDuplicateQuestionsWithDifferentTypes(){
        Map<String, ReturnType> types = new HashMap<>();
        for (Question question : form.questions) {
            types.put(question.name, question.type);
        }
        Set<String> duplicateQuestionsWithDifferentType = new HashSet<>();
        for (Question question : form.questions) {
            if(types.containsKey(question.name) && !types.get(question.name).eq(question.type)){
                duplicateQuestionsWithDifferentType.add(question.name + " " + question.getLocation());
            }
        }
        return duplicateQuestionsWithDifferentType;
    }

    private ReturnType checkBinaryArithmetic(ExpressionBinary expression, String operation) {
        // Check whether operation can be applied to left and right model.expression
        boolean selfValid = expression.left.accept(this).isNumber() && expression.right.accept(this).isNumber();

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-numeric value in expression " + expression.getLocation());
        }

        return ReturnType.NUMBER;
    }

    private ReturnType checkBinaryComparison(ExpressionBinary expression, String operation) {
        // Check whether operation can be applied to left and right model.expression
        boolean selfValid = expression.left.accept(this).isNumber() && expression.right.accept(this).isNumber();

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-numeric value in expression" + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    private ReturnType checkBinaryBoolean(ExpressionBinary expression, String operation) {
        // Check whether operation can be applied to left and right model.expression
        boolean selfValid = expression.left.accept(this) == ReturnType.BOOLEAN
                && expression.right.accept(this) == ReturnType.BOOLEAN;

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-boolean value in expression" + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticDivide expression) {
        return checkBinaryArithmetic(expression, "division");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticMultiply expression) {
        return checkBinaryArithmetic(expression, "multiplication");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSubtract expression) {
        return checkBinaryArithmetic(expression, "subtraction");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSum expression) {
        return checkBinaryArithmetic(expression, "addition");
    }

    @Override
    public ReturnType visit(ExpressionComparisonEq expression) {
        boolean selfValid = expression.left.accept(this) == expression.right.accept(this);

        if (!selfValid) {
            errors.add("Invalid EQ: comparing values of different types" + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionComparisonGE expression) {
        return checkBinaryComparison(expression, "GE");
    }

    @Override
    public ReturnType visit(ExpressionComparisonGT expression) {
        return checkBinaryComparison(expression, "GT");
    }

    @Override
    public ReturnType visit(ExpressionComparisonLE expression) {
        return checkBinaryComparison(expression, "LE");
    }

    @Override
    public ReturnType visit(ExpressionComparisonLT expression) {
        return checkBinaryComparison(expression, "LT");
    }

    @Override
    public ReturnType visit(ExpressionLogicalAnd expression) {
        return checkBinaryBoolean(expression, "AND");
    }

    @Override
    public ReturnType visit(ExpressionLogicalOr expression) {
        return checkBinaryBoolean(expression, "OR");
    }

    @Override
    public ReturnType visit(ExpressionUnaryNot expression) {
        boolean selfValid = expression.value.accept(this) == ReturnType.BOOLEAN;

        if (!selfValid) {
            errors.add("Invalid NOT: non-boolean expression " + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionUnaryNeg expression) {
        boolean selfValid = expression.value.accept(this) == ReturnType.NUMBER;

        if (!selfValid) {
            errors.add("Invalid NEG: non-numeric expression " + expression.getLocation());
        }

        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableBoolean expression) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionVariableDate expression) {
        return ReturnType.DATE;
    }

    @Override
    public ReturnType visit(ExpressionVariableInteger expression) {
        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableDecimal expression) {
        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableMoney expression) {
        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableString expression) {
        return ReturnType.STRING;
    }

    @Override
    public ReturnType visit(ExpressionVariableUndefined expression) {
        return expression.getReturnType();
    }

    @Override
    public ReturnType visit(ExpressionIdentifier expression) {
        if (this.symbolTable.containsExpression(expression.identifier)) {
            return this.symbolTable.getExpression(expression.identifier).accept(this);
        } else {
            errors.add("Cannot get value for unknown field '" + expression.identifier + "' " + expression.getLocation());
            return ReturnType.UNDEFINED;
        }
    }
}
