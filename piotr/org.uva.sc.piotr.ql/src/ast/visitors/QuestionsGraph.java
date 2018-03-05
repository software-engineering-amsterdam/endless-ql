package ast.visitors;

import ast.model.Form;
import ast.model.datatypes.*;
import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;
import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.UnaryExpression;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.unary.values.Literal;
import ast.model.expressions.unary.values.VariableReference;
import ast.model.statement.IfStatement;
import ast.model.statement.Question;
import ast.model.statement.Statement;

public class QuestionsGraph implements ASTNodeVisitor {



    @Override
    public void visit(Form form) {

    }

    @Override
    public void visit(Statement statement) {

    }

    @Override
    public void visit(Question question) {

    }

    @Override
    public void visit(IfStatement ifStatement) {

    }

    @Override
    public void visit(Expression expression) {

    }

    @Override
    public void visit(UnaryExpression unaryExpression) {

    }

    @Override
    public void visit(Literal literal) {

    }

    @Override
    public void visit(VariableReference variableReference) {

    }

    @Override
    public void visit(Negation negation) {

    }

    @Override
    public void visit(Minus minus) {

    }

    @Override
    public void visit(BinaryExpression binaryExpression) {

    }

    @Override
    public void visit(Addition addition) {

    }

    @Override
    public void visit(Subtraction subtraction) {

    }

    @Override
    public void visit(Division division) {

    }

    @Override
    public void visit(Multiplication multiplication) {

    }

    @Override
    public void visit(Equal equal) {

    }

    @Override
    public void visit(GreaterEqual greaterEqual) {

    }

    @Override
    public void visit(GreaterThan greaterThan) {

    }

    @Override
    public void visit(LessEqual lessEqual) {

    }

    @Override
    public void visit(LessThan lessThan) {

    }

    @Override
    public void visit(NotEqual notEqual) {

    }

    @Override
    public void visit(LogicalAnd logicalAnd) {

    }

    @Override
    public void visit(LogicalOr logicalOr) {

    }

    @Override
    public void visit(TypeDeclaration typeDeclaration) {

    }

    @Override
    public void visit(TypeDeclarationBoolean typeDeclarationBoolean) {

    }

    @Override
    public void visit(TypeDeclarationDecimal typeDeclarationDecimal) {

    }

    @Override
    public void visit(TypeDeclarationInteger typeDeclarationInteger) {

    }

    @Override
    public void visit(TypeDeclarationString typeDeclarationString) {

    }
}
