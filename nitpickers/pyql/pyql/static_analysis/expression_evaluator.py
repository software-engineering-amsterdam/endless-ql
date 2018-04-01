from util.multimethods import multimethod
from pyql.ast.expression.expressions import *
from util import errors


class ExpressionEvaluator:

    def __init__(self, symbol_table):
        self.symbol_table = symbol_table

    @multimethod(Identifier)
    def visit(self, identifier):
        return self.symbol_table.get(identifier.identifier)

    @multimethod(Literal)
    def visit(self, literal):
        return literal.value

    @multimethod(Multiplication)
    def visit(self, multiplication):
        return multiplication.left.accept(self) * multiplication.right.accept(self)

    @multimethod(Division)
    def visit(self, division):
        try:
            return division.left.accept(self) / division.right.accept(self)
        except ZeroDivisionError:
            raise errors.ZeroDivision(str(division.left) + " / " + str(division.right) + " can not divide by zero.")

    @multimethod(Addition)
    def visit(self, addition):
        return addition.left.accept(self) + addition.right.accept(self)

    @multimethod(Subtraction)
    def visit(self, subtraction):
        return subtraction.left.accept(self) - subtraction.right.accept(self)

    @multimethod(GreaterThan)
    def visit(self, greater_than):
        return greater_than.left.accept(self) > greater_than.right.accept(self)

    @multimethod(LessThan)
    def visit(self, less_than):
        return less_than.left.accept(self) < less_than.right.accept(self)

    @multimethod(GreaterThanOrEqual)
    def visit(self, greater_than_or_equal):
        return greater_than_or_equal.left.accept(self) >= greater_than_or_equal.right.accept(self)

    @multimethod(LessThanOrEqual)
    def visit(self, less_than_or_equal):
        return less_than_or_equal.left.accept(self) <= less_than_or_equal.right.accept(self)

    @multimethod(Equals)
    def visit(self, equals):
        return equals.left.accept(self) == equals.right.accept(self)

    @multimethod(NotEquals)
    def visit(self, not_equals):
        return not_equals.left.accept(self) != not_equals.right.accept(self)

    @multimethod(And)
    def visit(self, and_expression):
        return and_expression.left.accept(self) and and_expression.right.accept(self)

    @multimethod(Or)
    def visit(self, or_expression):
        return or_expression.left.accept(self) or or_expression.right.accept(self)

    @multimethod(Not)
    def visit(self, not_unary_expression):
        return not not_unary_expression.expression.accept(self)

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        pass

    @multimethod(BinaryExpression)
    def visit(self, binary_expression):
        pass

    @multimethod(Expression)
    def visit(self, expression):
        pass
