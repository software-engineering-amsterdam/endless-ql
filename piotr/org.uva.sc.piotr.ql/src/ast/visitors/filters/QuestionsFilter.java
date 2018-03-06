package ast.visitors.filters;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.unary.values.VariableReference;
import ast.model.statements.Question;
import ast.visitors.ASTNodeAbstractVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class QuestionsFilter extends ASTNodeAbstractVisitor {

    private HashMap<Question, ArrayList<VariableReference>> questionsMap = new HashMap<>();

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(this.questionsMap.keySet());
    }

    public HashMap<Question, ArrayList<VariableReference>> getQuestionsMap() {
        return questionsMap;
    }

    @Override
    public void visit(Question question) {

        ArrayList<VariableReference> references = new ArrayList<>();

        if (question.getAssignedExpression() != null) {
            references = this.visitForReferences(question.getAssignedExpression());
        }

        this.questionsMap.put(question, references);

    }

    private ArrayList<VariableReference> visitForReferences(Expression expression) {
        if (expression instanceof Negation) {
            return visitForReferences((Negation) expression);
        } else if (expression instanceof Minus) {
            return visitForReferences((Minus) expression);
        } else if (expression instanceof Multiplication) {
            return visitForReferences((Multiplication) expression);
        } else if (expression instanceof Division) {
            return visitForReferences((Division) expression);
        } else if (expression instanceof Addition) {
            return visitForReferences((Addition) expression);
        } else if (expression instanceof Subtraction) {
            return visitForReferences((Subtraction) expression);
        } else if (expression instanceof GreaterThan) {
            return visitForReferences((GreaterThan) expression);
        } else if (expression instanceof GreaterEqual) {
            return visitForReferences((GreaterEqual) expression);
        } else if (expression instanceof LessEqual) {
            return visitForReferences((LessEqual) expression);
        } else if (expression instanceof Equal) {
            return visitForReferences((Equal) expression);
        } else if (expression instanceof NotEqual) {
            return visitForReferences((NotEqual) expression);
        } else if (expression instanceof LogicalAnd) {
            return visitForReferences((LogicalAnd) expression);
        } else if (expression instanceof LogicalOr) {
            return visitForReferences((LogicalOr) expression);
        } else if (expression instanceof VariableReference) {
            return visitForReferences((VariableReference) expression);
        }
        return new ArrayList<>();
    }

    private ArrayList<VariableReference> visitForReferences(Negation expression) {
        return visitForReferences(expression.getExpression());
    }

    private ArrayList<VariableReference> visitForReferences(Minus expression) {
        return visitForReferences(expression.getExpression());
    }

    private ArrayList<VariableReference> visitForReferences(Multiplication expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(Division expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(Addition expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(Subtraction expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(GreaterThan expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(GreaterEqual expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(LessEqual expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(Equal expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(NotEqual expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(LogicalAnd expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(LogicalOr expression) {
        ArrayList<VariableReference> aggregatedList = new ArrayList<>();
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getLeftSide())));
        aggregatedList.addAll(Objects.requireNonNull(visitForReferences(expression.getRightSide())));
        return aggregatedList;
    }

    private ArrayList<VariableReference> visitForReferences(VariableReference expression) {
        ArrayList<VariableReference> result = new ArrayList<>();
        result.add(expression);
        return result;
    }

}
