package ql.analysis;

import ql.evaluation.Binding;
import ql.evaluation.IExpressionVisitor;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionBinary;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.ReturnType;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.util.*;

public class TypeChecker implements IExpressionVisitor<ReturnType> {

    private final Form form;
    private final Set<String> errors;

    public TypeChecker(Form form) {
        this.form = form;
        this.errors = new HashSet<>();
    }

    public Set<String> getErrors() {
        return this.errors;
    }

    public Set<String> typeCheck() {
        ReferencedIdentifiersVisitor referencedIdentifiersVisitor = new ReferencedIdentifiersVisitor();
        List<Binding> bindings = referencedIdentifiersVisitor.getBindings(form);

        for (Question question : form.questions) {
            this.visit(question.condition, bindings);

            // Check if question type is same as assigned expression type
            // Only check expression when it is a predefined expression
            if (question.isComputed()) {
                ReturnType defaultAnswerType = this.visit(question.defaultAnswer, bindings);

                // Any type of number expression can be assigned to another number type field
                ReturnType questionType = question.type.isNumber() ? ReturnType.NUMBER : question.type;

                if (defaultAnswerType != questionType) {
                    errors.add("Invalid assignment: cannot assign " + defaultAnswerType + " to " + question.type +
                            question.getLocation());
                }
            }
        }

        return this.errors;
    }

    public Set<String> checkDuplicateQuestionsWithDifferentTypes() {
        Map<String, ReturnType> types = new HashMap<>();
        Set<String> duplicateQuestionsWithDifferentType = new HashSet<>();

        for (Question question : form.questions) {
            if(types.containsKey(question.name) && types.get(question.name) != question.type) {
                duplicateQuestionsWithDifferentType.add(question.name + " " + question.getLocation());
            } else {
                types.put(question.name, question.type);
            }
        }

        return duplicateQuestionsWithDifferentType;
    }

    private ReturnType checkBinaryArithmetic(ExpressionBinary expression, String operation, List<Binding> bindings) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = expression.left.accept(this, bindings).isNumber()
                && expression.right.accept(this, bindings).isNumber();

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-numeric value in expression " + expression.getLocation());
        }

        return ReturnType.NUMBER;
    }

    private ReturnType checkBinaryComparison(ExpressionBinary expression, String operation, List<Binding> bindings) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = expression.left.accept(this, bindings).isNumber()
                && expression.right.accept(this, bindings).isNumber();

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-numeric value in expression" + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    private ReturnType checkBinaryBoolean(ExpressionBinary expression, String operation, List<Binding> bindings) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = expression.left.accept(this, bindings) == ReturnType.BOOLEAN
                && expression.right.accept(this, bindings) == ReturnType.BOOLEAN;

        if (!selfValid) {
            errors.add("Invalid " + operation + ": non-boolean value in expression" + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(Expression expression, List<Binding> bindings) {
        return expression.accept(this, bindings);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticDivide expression, List<Binding> bindings) {
        return checkBinaryArithmetic(expression, "division", bindings);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticMultiply expression, List<Binding> bindings) {
        return checkBinaryArithmetic(expression, "multiplication", bindings);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSubtract expression, List<Binding> bindings) {
        return checkBinaryArithmetic(expression, "subtraction", bindings);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSum expression, List<Binding> bindings) {
        return checkBinaryArithmetic(expression, "addition", bindings);
    }

    @Override
    public ReturnType visit(ExpressionComparisonEq expression, List<Binding> bindings) {
        boolean selfValid = expression.left.accept(this, bindings) == expression.right.accept(this, bindings);

        if (!selfValid) {
            errors.add("Invalid EQ: comparing values of different types" + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionComparisonGE expression, List<Binding> bindings) {
        return checkBinaryComparison(expression, "GE", bindings);
    }

    @Override
    public ReturnType visit(ExpressionComparisonGT expression, List<Binding> bindings) {
        return checkBinaryComparison(expression, "GT", bindings);
    }

    @Override
    public ReturnType visit(ExpressionComparisonLE expression, List<Binding> bindings) {
        return checkBinaryComparison(expression, "LE", bindings);
    }

    @Override
    public ReturnType visit(ExpressionComparisonLT expression, List<Binding> bindings) {
        return checkBinaryComparison(expression, "LT", bindings);
    }

    @Override
    public ReturnType visit(ExpressionLogicalAnd expression, List<Binding> bindings) {
        return checkBinaryBoolean(expression, "AND", bindings);
    }

    @Override
    public ReturnType visit(ExpressionLogicalOr expression, List<Binding> bindings) {
        return checkBinaryBoolean(expression, "OR", bindings);
    }

    @Override
    public ReturnType visit(ExpressionUnaryNot expression, List<Binding> bindings) {
        boolean selfValid = expression.value.accept(this, bindings) == ReturnType.BOOLEAN;

        if (!selfValid) {
            errors.add("Invalid NOT: non-boolean expression " + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionUnaryNeg expression, List<Binding> bindings) {
        boolean selfValid = expression.value.accept(this, bindings) == ReturnType.NUMBER;

        if (!selfValid) {
            errors.add("Invalid NEG: non-numeric expression " + expression.getLocation());
        }

        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableBoolean expression, List<Binding> bindings) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionVariableDate expression, List<Binding> bindings) {
        return ReturnType.DATE;
    }

    @Override
    public ReturnType visit(ExpressionVariableInteger expression, List<Binding> bindings) {
        return ReturnType.INTEGER;
    }

    @Override
    public ReturnType visit(ExpressionVariableDecimal expression, List<Binding> bindings) {
        return ReturnType.DECIMAL;
    }

    @Override
    public ReturnType visit(ExpressionVariableMoney expression, List<Binding> bindings) {
        return ReturnType.MONEY;
    }

    @Override
    public ReturnType visit(ExpressionVariableString expression, List<Binding> bindings) {
        return ReturnType.STRING;
    }

    @Override
    public ReturnType visit(ExpressionVariableUndefined expression, List<Binding> bindings) {
        return expression.getReturnType();
    }

    @Override
    public ReturnType visit(ExpressionIdentifier expression, List<Binding> bindings) {
        return Lookup.lookup(expression, bindings).accept(this, bindings);
    }
}
