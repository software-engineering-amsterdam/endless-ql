from pyql.ast.expression import expressions
from pyql.util import types


class StringLiteral(expressions.Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = str(value)
        self._type = types.String()

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    def accept(self, visitor):
        return visitor.visit_string_literal(self)


class IntegerLiteral(expressions.Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = int(value)
        self._type = types.Integer

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    def accept(self, visitor):
        return visitor.visit_integer_literal(self)


class DecimalLiteral(expressions.Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value
        self._type = types.Decimal

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    def accept(self, visitor):
        return visitor.visit_decimal_literal(self)


class BooleanLiteral(expressions.Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value
        self._type = types.Boolean()

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    def accept(self, visitor):
        return visitor.visit_boolean_literal(self)


class MoneyLiteral(expressions.Literal):

    def __init__(self, location, value):
        super().__init__(location)
        self._value = value
        self._type = types.Money()

    @property
    def value(self):
        return self._value

    @property
    def type(self):
        return self._type

    def accept(self, visitor):
        return visitor.visit_money_literal(self)
