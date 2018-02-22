from pyql.ast import ast
from pyql.ast import code_location


class Expression(ast.ASTNode):

    def __init__(self, location):
        super().__init__(location)


class Identifier(Expression):

    def __init__(self, identifier: str, location):
        super().__init__(location)
        self._identifier = identifier

    @property
    def identifier(self):
        return self._identifier


class UnaryExpression(Expression):

    def __init__(self, location, expression):
        super().__init__(location)
        self._expression = expression

    @property
    def expression(self):
        return self._expression


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


class Multiplication(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " * " + str(self.right)


class Division(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " / " + str(self.right)


class Addition(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " + " + str(self.right)


class Subtraction(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " - " + str(self.right)


class GreaterThan(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " > " + str(self.right)


class LessThan(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " < " + str(self.right)


class GreaterThanOrEqual(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " >= " + str(self.right)


class LessThanOrEqual(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " <= " + str(self.right)


class Equals(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " == " + str(self.right)


class NotEquals(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " != " + str(self.right)


class And(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " && " + str(self.right)


class Or(BinaryExpression):

    def __init__(self, location, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " || " + str(self.right)


class Not(UnaryExpression):

    def __init__(self, location, expression):
        super().__init__(location, expression)

    def __repr__(self):
        return "!(" + str(self.expression) + ")"


class Literal(Expression):

    def __init__(self, location):
        super().__init__(location)


class StringLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value

    @property
    def value(self):
        return self._value


class IntLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = int(value)

    @property
    def value(self):
        return self._value


class DecimalLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = float(value)

    @property
    def value(self):
        return self._value


class BoolLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value == "true"

    @property
    def value(self):
        return self._value


class MoneyLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value

    @property
    def value(self):
        return self._value


if __name__ == "__main__":
    loc = code_location.CodeLocation(2, 3)
    b = And(2, "left", "right")
    # c = Not(loc, b)
    c = "True" == "True"
    print(c)
    # print(c)
