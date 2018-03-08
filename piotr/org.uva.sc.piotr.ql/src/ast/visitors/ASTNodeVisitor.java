package ast.visitors;

import ast.model.Form;
import ast.model.datatypes.TypeDeclarationBoolean;
import ast.model.datatypes.TypeDeclarationDecimal;
import ast.model.datatypes.TypeDeclarationInteger;
import ast.model.datatypes.TypeDeclarationString;
import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.values.Literal;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.IfStatement;
import ast.model.statements.Question;

public interface ASTNodeVisitor<T> {

    T visit(Form form);

    T visit(Question question);

    T visit(IfStatement ifStatement);

    T visit(Literal literal);

    T visit(VariableReference variableReference);

    T visit(Negation negation);

    T visit(Minus minus);

    T visit(Addition addition);

    T visit(Subtraction subtraction);

    T visit(Division division);

    T visit(Multiplication multiplication);

    T visit(Equal equal);

    T visit(GreaterEqual greaterEqual);

    T visit(GreaterThan greaterThan);

    T visit(LessEqual lessEqual);

    T visit(LessThan lessThan);

    T visit(NotEqual notEqual);

    T visit(LogicalAnd logicalAnd);

    T visit(LogicalOr logicalOr);

    T visit(TypeDeclarationBoolean typeDeclarationBoolean);

    T visit(TypeDeclarationDecimal typeDeclarationDecimal);

    T visit(TypeDeclarationInteger typeDeclarationInteger);

    T visit(TypeDeclarationString typeDeclarationString);
    
}
