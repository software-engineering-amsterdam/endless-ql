package org.uva.forcepushql.ast;

public interface ASTNodes {

    abstract class Node{}

    class QuestionFormat extends Node{}

    class Conditional extends Node {}

    abstract class Expression extends Node {}

    abstract class BinaryExpression extends Expression {
        public Expression left;
        public Expression right;
    }

    class PlusExpression extends BinaryExpression {}

    class MinusExpression extends BinaryExpression {}

    class MultiplyExpression extends BinaryExpression {}

    class DivideExpression extends BinaryExpression {}

    class AndExpression extends BinaryExpression {}

    class OrExpression extends BinaryExpression {}

    class LessExpression extends BinaryExpression {}

    class GreaterExpression extends BinaryExpression {}

    class EqualGreaterExpression extends BinaryExpression {}

    class EqualLessExpression extends BinaryExpression {}

    class NotEqualExpression extends BinaryExpression {}

    class IsEqualExpression extends BinaryExpression {}
}
