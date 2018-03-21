package ql.ast.model.declarations;

import ql.ast.model.expressions.Expression;

public interface TypeDeclarationInterface {
    Expression.DataType toDataType();
}
