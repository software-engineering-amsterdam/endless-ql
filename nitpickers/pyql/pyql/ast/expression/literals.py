from pyql.ast.expression.expressions import Literal
from pyql.util import types
import decimal


class StringLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.String, str(value))

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class IntegerLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Integer, int(value))

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class DecimalLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Decimal, decimal.Decimal(value))

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class BooleanLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Boolean, value)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class MoneyLiteral(Literal):

    def __init__(self, location, value):
        super().__init__(location, types.Money, round(decimal.Decimal(value), 2))

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


if __name__ == "__main__":
    d = DecimalLiteral(None, 34.3)
    print(d)
