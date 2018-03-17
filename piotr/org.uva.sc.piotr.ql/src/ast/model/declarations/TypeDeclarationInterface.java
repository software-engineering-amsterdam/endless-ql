package ast.model.declarations;

import ast.model.expressions.Expression;

public interface TypeDeclarationInterface {
    Expression.DataType toDataType();
}
