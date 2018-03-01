from pyql.ast.expression.expressions import Literal
from pyql.util import types


class StringLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.String(), str(value))

    def accept(self, visitor):
        return visitor.visit_string_literal(self)


class IntegerLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Integer(), int(value))

    def accept(self, visitor):
        return visitor.visit_integer_literal(self)


class DecimalLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Decimal(), value)

    def accept(self, visitor):
        return visitor.visit_decimal_literal(self)


class BooleanLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Boolean(), value == "true")

    def accept(self, visitor):
        return visitor.visit_boolean_literal(self)


class MoneyLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Money(), value)

    def accept(self, visitor):
        return visitor.visit_money_literal(self)


if __name__ == "__main__":
    d = DecimalLiteral(None, 34.3)
    print(d)
