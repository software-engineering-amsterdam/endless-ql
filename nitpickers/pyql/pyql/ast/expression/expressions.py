from pyql.ast.ast import ASTNode
from pyql.ast import code_location


class Expression(ASTNode):

    def __init__(self, location: code_location.CodeLocation):
        super().__init__(location)


class Identifier(Expression):

    def __init__(self, identifier: str, location: code_location.CodeLocation):
        super().__init__(location)
        self.identifier = identifier


class UnaryExpression(Expression):

    def __init__(self, location: code_location.CodeLocation, expression):
        super().__init__(location)
        self.expression = expression


class BinaryExpression(Expression):

    def __init__(self, location: code_location.CodeLocation, left, right):
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

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " * " + str(self.right)


class Division(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " / " + str(self.right)


class GreaterThan(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " > " + str(self.right)


class LessThan(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " < " + str(self.right)


class GreaterThanOrEqual(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " >= " + str(self.right)


class LessThanOrEqual(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " <= " + str(self.right)


class Equals(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " == " + str(self.right)


class NotEquals(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " != " + str(self.right)


class And(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " && " + str(self.right)


class Or(BinaryExpression):

    def __init__(self, location: code_location.CodeLocation, left, right):
        super().__init__(location, left, right)

    def __repr__(self):
        return str(self.left) + " || " + str(self.right)


class Not(UnaryExpression):

    def __init__(self, location: code_location.CodeLocation, expression):
        super().__init__(location, expression)

    def __repr__(self):
        return "!" + str(self.expression)


if __name__ == "__main__":
    location = code_location.CodeLocation(2, 3)
    b = And(location, "left", "right")
    c = Not(location, b)
    print(b)
    print(c)
