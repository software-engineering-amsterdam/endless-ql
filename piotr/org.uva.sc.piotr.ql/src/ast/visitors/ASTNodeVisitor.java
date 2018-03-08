package ast.visitors;

import ast.model.Form;
import ast.model.datatypes.*;
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

public interface ASTNodeVisitor {

    void visit(Form form);

    void visit(Question question);

    void visit(IfStatement ifStatement);

    void visit(Literal literal);

    void visit(VariableReference variableReference);

    void visit(Negation negation);

    void visit(Minus minus);

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

    void visit(TypeDeclarationBoolean typeDeclarationBoolean);

    void visit(TypeDeclarationDecimal typeDeclarationDecimal);

    void visit(TypeDeclarationInteger typeDeclarationInteger);

    void visit(TypeDeclarationString typeDeclarationString);
    
}
