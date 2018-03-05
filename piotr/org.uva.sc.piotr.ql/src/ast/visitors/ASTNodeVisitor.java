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

public interface ASTNodeVisitor {

    void visit(Form form);

    void visit(Statement statement);

    void visit(Question question);

    void visit(IfStatement ifStatement);

    void visit(Expression expression);

    void visit(UnaryExpression unaryExpression);

    void visit(Literal literal);

    void visit(VariableReference variableReference);

    void visit(Negation negation);

    void visit(Minus minus);

    void visit(BinaryExpression binaryExpression);

    void visit(Addition addition);

    void visit(Subtraction subtraction);

    void visit(Division division);

    void visit(Multiplication multiplication);

    void visit(Equal equal);

    void visit(GreaterEqual greaterEqual);

    void visit(GreaterThan greaterThan);

    void visit(LessEqual lessEqual);

    void visit(LessThan lessThan);

    void visit(NotEqual notEqual);

    void visit(LogicalAnd logicalAnd);

    void visit(LogicalOr logicalOr);

    void visit(TypeDeclaration typeDeclaration);

    void visit(TypeDeclarationBoolean typeDeclarationBoolean);

    void visit(TypeDeclarationDecimal typeDeclarationDecimal);

    void visit(TypeDeclarationInteger typeDeclarationInteger);

    void visit(TypeDeclarationString typeDeclarationString);
    
}
