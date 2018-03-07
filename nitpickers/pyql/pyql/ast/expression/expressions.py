from pyql.ast import ast
from pyql.ast import code_location


class Expression(ast.ASTNode):

    def __init__(self, location):
        super().__init__(location)


class Identifier(Expression):

    def __init__(self, location, identifier):
        super().__init__(location)
        self._identifier = identifier

    @property
    def identifier(self):
        return self._identifier

    def __repr__(self):
        return str(self.identifier)


class UnaryExpression(Expression):

    def __init__(self, location, expression):
        super().__init__(location)
        self._expression = expression

    @property
    def expression(self):
        return self._expression

    def accept(self, visitor):
        return visitor.visit_unary_expression(self)


class BinaryExpression(Expression):  # abstract

    def __init__(self, location, left, right):
        super().__init__(location)
        self._left = left
        self._right = right

    @property
    def left(self):
        return self._left

    @property
    def right(self):
        return self._right

    def accept(self, visitor):
        return visitor.visit_binary_expression(self)


class Multiplication(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " * " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_multiplication(self)


class Division(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " / " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_division(self)


class Addition(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " + " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_addition(self)


class Subtraction(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " - " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_subtraction(self)


class GreaterThan(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " > " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_greater_than(self)


class LessThan(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " < " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_less_than(self)


class GreaterThanOrEqual(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " >= " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_greater_than_or_equal(self)


class LessThanOrEqual(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " <= " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_less_than_or_equal(self)


class Equals(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " == " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_equals(self)


class NotEquals(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " != " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_not_equals(self)


class And(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " && " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_and(self)


class Or(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " || " + str(self.right)

    def accept(self, visitor):
        return visitor.visit_or(self)


class Not(UnaryExpression):

    def __init__(self, location, expression):
        super().__init__(location, expression)

    def __repr__(self):
        return "!(" + str(self.expression) + ")"

    def accept(self, visitor):
        return visitor.visit_not(self)


class Literal(Expression):

    def __init__(self, location, type, value):
        super().__init__(location)
        self._value = value
        self._type = type

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, value):
        self._value = value

    @property
    def type(self):
        return self._type

    def accept(self, visitor):
        return visitor.visit_literal(self)

    def __repr__(self):
        return str(self._value)


if __name__ == "__main__":
    loc = code_location.CodeLocation(2, 3)
    b = And(2, "left", "right")
    # c = Not(loc, b)
    c = "True" == "True"
    print(c)
    # print(c)
