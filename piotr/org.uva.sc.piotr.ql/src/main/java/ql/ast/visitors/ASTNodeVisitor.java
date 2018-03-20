package ql.ast.visitors;

import ql.ast.model.Form;
import ql.ast.model.declarations.*;
import ql.ast.model.expressions.binary.arithmetics.Addition;
import ql.ast.model.expressions.binary.arithmetics.Division;
import ql.ast.model.expressions.binary.arithmetics.Multiplication;
import ql.ast.model.expressions.binary.arithmetics.Subtraction;
import ql.ast.model.expressions.binary.comparision.*;
import ql.ast.model.expressions.binary.logical.LogicalAnd;
import ql.ast.model.expressions.binary.logical.LogicalOr;
import ql.ast.model.expressions.unary.arithmetics.Minus;
import ql.ast.model.expressions.unary.logical.Negation;
import ql.ast.model.expressions.values.Literal;
import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.IfStatement;
import ql.ast.model.statements.Question;

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

    T visit(TypeDeclarationMoney typeDeclarationMoney);

    T visit(TypeDeclarationInteger typeDeclarationInteger);

    T visit(TypeDeclarationString typeDeclarationString);
    
}
